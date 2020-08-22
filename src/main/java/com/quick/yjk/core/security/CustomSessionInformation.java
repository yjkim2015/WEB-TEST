package com.quick.yjk.core.security;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.security.core.session.SessionInformation;

/**
 * Custom 세션 정보
 * 
 * @author 배수현
 *
 */
public class CustomSessionInformation extends SessionInformation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * IP 주소
	 */
	private transient String ipAddress;
	
	/**
	 * 로그인 타입
	 */
	private transient String loginType;

	/**
	 * 생성자
	 * @param principal
	 * @param sessionId
	 * @param lastRequest
	 */
	public CustomSessionInformation(final Object principal, final String sessionId, final Date lastRequest) {
		super(principal, sessionId, lastRequest);
	}

	/**
	 * 생성자
	 * @param principal
	 * @param sessionId
	 * @param lastRequest
	 * @param ipAddress
	 * @param loginType
	 */
	public CustomSessionInformation(final Object principal, final String sessionId, final Date lastRequest, final String ipAddress, final String loginType) {
		super(principal, sessionId, lastRequest);
		
		this.ipAddress = ipAddress;
		this.loginType = loginType;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(final String loginType) {
		this.loginType = loginType;
	}

}
