package com.quick.yjk.common.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.quick.yjk.vo.UserVo;

public class CommonController {
	/**
	 * 유저 세션 정보 Attribute Key
	 */
	private static final String SESSION_USER = "USER";
	
	/**
	 * context path를 정보를 반환
	 * @param request
	 * @return
	 */
	@ModelAttribute("getContextPath")
	public String getContextPath(final HttpServletRequest request) {
		return request.getContextPath();
	}
	
	/**
	 * context path를 정보를 반환
	 * @param request
	 * @return
	 */
	@ModelAttribute("sessionId")
	public String getSessionId(final HttpServletRequest request) {
		return request.getSession().getId();
	}
	
	/**
	 * 세션에 User 객체를 담는다.
	 * @param user
	 * @param request
	 */
	public void setSessionUser(final UserVo user, final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		session.setAttribute(SESSION_USER, user);
	}

	/**
	 * 세션에서 User 객체를 가져온다.
	 * @param request
	 * @return
	 */
	public UserVo getSessionUser(final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		return (UserVo)session.getAttribute(SESSION_USER);
	}
	
	/**
	 * 세션에서 User 객체를 가져온다.
	 * @param request
	 * @return
	 */
	public String getSessionUserLoginId(final HttpServletRequest request) {
		final HttpSession session = request.getSession();
		final UserVo userVo = (UserVo)session.getAttribute(SESSION_USER);
		return userVo == null ? "" : userVo.getLoginId();
	}
}