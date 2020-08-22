package com.quick.yjk.accounting.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.yjk.common.component.CommonController;
import com.quick.yjk.vo.UserVo;

@Controller
public class LoginController extends CommonController {
	/**
	 * 기본 PATH
	 */
	private static final String BASE_PATH = "/login/%s";
	
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
		final UserVo userVo = getSessionUser(request);
		if (userVo != null) {
			response.sendRedirect("/main");
		}
		
		return String.format(BASE_PATH, "index");	
	}
}