package com.quick.yjk.core.security;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.quick.yjk.vo.UserVo;

public class LoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginHandler.class);
	
	/**
	 * 이력 관리 Service
	 */
//	@Autowired
//	private transient HistoryService historyService;
	
	
	/**
	 * 세션 정보
	 */
	@Resource(name="sessionRegistrySessionIds")
	private transient Map<String, SessionInformation> sessionIds;
	
	/**
	 * 로그인 실패했을 경우 실패 메세지를 반환
	 */
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		response.getWriter().print("{\"result\":\"FAIL\", \"reason\":\"ID / PASSOWRD를 확인하세요.\"}");
		response.getWriter().flush();
		
	}

	/**
	 * 로그인 성공했을 경우 성공 메세지를 반환
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
		final HttpSession session = request.getSession();
		//session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		final UserVo userVo = (UserVo) authentication.getDetails();
		userVo.setSessionId(session.getId());
		userVo.setIpAddress(request.getRemoteAddr());
//		session.setAttribute("USER", userVo);
		
		final SessionInformation information = new CustomSessionInformation(authentication.getDetails(), session.getId(), new Date(), request.getRemoteAddr(), userVo.getLoginType());
		
//		sessionIds.put(session.getId(), information);

		String redirectUrl = request.getContextPath() + "/"+userVo.getRole()+"/main";
		final String requestURI = (String)session.getAttribute("requestURI");
		if (requestURI != null ) {
			redirectUrl = requestURI;
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print("{\"result\":\"OK\",\"redirectUrl\":\"" + redirectUrl + "\"}");
		response.getWriter().flush();
	}
}
