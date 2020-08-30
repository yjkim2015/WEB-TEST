package com.quick.yjk.vo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.quick.yjk.common.constants.RoleType;


public class UserVo extends SettableVo implements Serializable, UserDetails {
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
	private String userName;
	
	private String brandName;
	
	private String phone;
	
	private int driverNum;
	
	private boolean confirm;
	
	/**
	 * 생성일
	 */
	private String createDate;
	
	/**
	 * 최근 로그인
	 */
	private String lastLoginDate;
	
	private RoleType role;
	
	private String username; // ID
	private String password; // PW
	
	private  String series;
	private  String token;
	private  Date lastUsed;
	private List<GrantedAuthority> authorities;
	
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


	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDriverNum() {
		return driverNum;
	}

	public void setDriverNum(int driverNum) {
		this.driverNum = driverNum;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "UserVo [loginType=" + loginType + ", sessionId=" + sessionId + ", ipAddress=" + ipAddress + ", loginId="
				+ loginId + ", passwd=" + passwd + ", userName=" + userName + ", brandName=" + brandName + ", phone="
				+ phone + ", driverNum=" + driverNum + ", confirm=" + confirm + ", createDate=" + createDate
				+ ", lastLoginDate=" + lastLoginDate + ", role=" + role+ "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (int i = 0; i < authList.size(); i++) {
			authorities.add(new SimpleGrantedAuthority(authList.get(i).toString()));
		}

		this.authorities = authorities;
	}

	@Override
	// 계정이 만료 되지 않았는가?
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	// 계정이 잠기지 않았는가?
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	// 패스워드가 만료되지 않았는가?
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	// 계정이 활성화 되었는가?
	public boolean isEnabled() {

		return true;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}
