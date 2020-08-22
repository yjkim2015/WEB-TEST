package com.quick.yjk.vo;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


public class UserVo extends SettableVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 로그인 타입
	 */
	private String loginType;
	
	/**
	 * 세션 ID
	 */
	private String sessionId;
	
	/**
	 * 로그인 IP
	 */
	private String ipAddress;
	
	/**
	 * 로그인 ID
	 */
	private String loginId;
	
	/**
	 * 패스워드
	 */
	private String passwd;			
	
	/**
	 * 운용자 명
	 */
	private String userNm;
	
	
	/**
	 * 생성일
	 */
	private String createDate;
	
	/**
	 * 최근 로그인
	 */
	private String lastLoginDate;
	
	
	/*
	 * Refresh or Close 여부
	 * */
	private Boolean refresh;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(final String passwd) {
		this.passwd = passwd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(final String userNm) {
		this.userNm = userNm;
	}


	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(final String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(final String loginType) {
		this.loginType = loginType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final String createDate) {
		this.createDate = createDate;
	}

	public Boolean getRefresh() {
		return refresh;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
