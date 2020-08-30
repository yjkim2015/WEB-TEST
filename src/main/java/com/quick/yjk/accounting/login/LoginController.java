package com.quick.yjk.accounting.login;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.yjk.common.component.CommonController;
import com.quick.yjk.core.security.UserLoginRememberMeService;
import com.quick.yjk.vo.UserVo;

@Controller
public class LoginController extends CommonController {
	/**
	 * 기본 PATH
	 */
	private static final String BASE_PATH = "/login/%s";
	
	private static final String DELIMITER = ":";
	
	@Autowired
	private UserLoginRememberMeService userLoginRememberMeService;
	
	/**
	 * 로그인 페이지
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login/index", method = RequestMethod.GET)
	public String index(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		String rememberMeCookie = null;
		
		if ((cookies == null) || (cookies.length == 0)) {
			System.out.println("쿠키 없쪄");
		}

		for (Cookie cookie : cookies) {
			System.out.println("cookie name :" + cookie.getName());
			if ("HSWEB_U_REMEMBER".equals(cookie.getName())) {
				rememberMeCookie =  cookie.getValue();
			}
		}
		
		if ( rememberMeCookie != null ) {
			String[] cookieTokens = decodeCookie(rememberMeCookie);
			UserVo user = (UserVo) userLoginRememberMeService.test(cookieTokens,request, response); 
			System.out.println("user : "+ user);
			if ( user != null ) {
				model.addAttribute("userVo", user); 
				response.sendRedirect("/"+user.getRole()+"/main");
			}
		}
		
		return String.format(BASE_PATH, "index");	

		
//		final UserVo userVo = getSessionUser(request);
//		System.out.println("request : " + request);
//		System.out.println("user : " + userVo);
//		if (userVo != null) {
//			model.addAttribute("userVo", userVo); 
//			response.sendRedirect("/"+userVo.getRole()+"/main");
//		}
//		
//		return String.format(BASE_PATH, "index");	
	}
	
	protected String[] decodeCookie(String cookieValue) throws InvalidCookieException {
		for (int j = 0; j < cookieValue.length() % 4; j++) {
			cookieValue = cookieValue + "=";
		}

		if (!Base64.isBase64(cookieValue.getBytes())) {
			throw new InvalidCookieException(
					"Cookie token was not Base64 encoded; value was '" + cookieValue
							+ "'");
		}

		String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

		String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText,
				DELIMITER);

		if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https"))
				&& tokens[1].startsWith("//")) {
			// Assume we've accidentally split a URL (OpenID identifier)
			String[] newTokens = new String[tokens.length - 1];
			newTokens[0] = tokens[0] + ":" + tokens[1];
			System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
			tokens = newTokens;
		}

		return tokens;
	}
}