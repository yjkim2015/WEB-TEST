package com.quick.yjk.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.quick.yjk.vo.UserVo;

public class LogoutHandler implements LogoutSuccessHandler {
	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutHandler.class);
	
	/**
	 * 로그아웃 성공시 수행
	 */
	@Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
		final String redirectUrl 	= String.format("%s/login/index", request.getContextPath()); 
		try {
			if ( authentication != null &&  authentication.getDetails() instanceof UserVo ) {
				response.sendRedirect(redirectUrl);
			}
			else {
				response.sendRedirect(redirectUrl);
				LOGGER.error("Authentication Error :" + authentication);
			}
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
    }
}
