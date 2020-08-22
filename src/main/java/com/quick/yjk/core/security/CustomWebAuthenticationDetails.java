package com.quick.yjk.core.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.quick.yjk.vo.UserVo;


/**
 * Custom Web Authentication Details
 * @author 배수현
 *
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 로그인 타입
	 */
	private String loginType;
	
	/**
	 * 운용자 정보
	 */
	private UserVo userVo;
	
	/**
	 * 생성자
	 * @param request
	 */
	public CustomWebAuthenticationDetails(final HttpServletRequest request) {
		super(request);
		
		this.loginType = request.getParameter("loginType");
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(final String loginType) {
		this.loginType = loginType;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(final UserVo userVo) {
		this.userVo = userVo;
	}
	
	
}
