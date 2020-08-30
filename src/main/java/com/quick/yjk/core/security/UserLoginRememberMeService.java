package com.quick.yjk.core.security;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.vo.UserVo;

public class UserLoginRememberMeService extends AbstractRememberMeServices{

	@Autowired
	private UserService userService;
	
	private SecureRandom random;
	
	protected UserLoginRememberMeService(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
		random = new SecureRandom();
	}

	/* 첫 로그인 시 쿠키 발행 및 토큰정보 DB 업데이트 */
	@Override
	protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication successfulAuthentication) {
		
		System.out.println("첫 로그인쓰???");
		// 사용자 쿠키 겁색
		//String cookieValue = super.extractRememberMeCookie(request);
		Cookie[] cookies = request.getCookies();
		String rememberMeCookie = null;
		for (Cookie cookie : cookies) {
			System.out.println("cookie name :" + cookie.getName());
			if ("HSWEB_U_REMEMBER".equals(cookie.getName())) {
				rememberMeCookie =  cookie.getValue();
			}
		}
		// 사용자 쿠키가 존재할 경우 DB에서 해당 데이터를 삭제함
		// 2차 인증하지 않으면 자동로그인 되지 못하는 쿠키가 남아있을 수 있음
		if (rememberMeCookie != null) {
	
			// series 값 기준으로 해당 토큰을 DB에서 삭제
			userService.removeUserTokens(decodeCookie(rememberMeCookie)[0]);
		}
	
		// 새로운 series, token 값 생성
		String username = successfulAuthentication.getName();
		String newSeriesValue = generateTokenValue();
		String newTokenValue = generateTokenValue();
		
		try {
			PersistentRememberMeToken newToken = new PersistentRememberMeToken(
					username,newSeriesValue, newTokenValue, new Date());

			userService.createNewToken(newToken);
			// 쿠키 발행
			String[] rawCookieValues = new String[] { newSeriesValue, newTokenValue };
			super.setCookie(rawCookieValues, getTokenValiditySeconds(), request, response);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/* 자동 로그인 로직 - 쿠키 유효성 검증 및 사용자 정보 객체 리턴 */
	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
			HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
		System.out.println("processAutoLoginCookie ");
		if (cookieTokens.length != 2) {
			throw new InvalidCookieException("Cookie token did not contain " + 2
					+ " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
		}

		final String presentedSeries = cookieTokens[0];
		final String presentedToken = cookieTokens[1];

		System.out.println("presentedSeries : " + presentedSeries);
		System.out.println("presentedToken : " + presentedToken);
		UserVo token = userService.getTokenForSeries(presentedSeries);

		if (token == null) {
			// No series match, so we can't authenticate using this cookie
			throw new RememberMeAuthenticationException(
					"No persistent token found for series id: " + presentedSeries);
		}

		// We have a match for this user/series combination
		if (!presentedToken.equals(token.getToken())) {
			// Token doesn't match series value. Delete all logins for this user and throw
			// an exception to warn them.
			userService.removeUserTokens(token.getUsername());

			throw new CookieTheftException(
					messages.getMessage(
							"PersistentTokenBasedRememberMeServices.cookieStolen",
							"Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
		}

		if (token.getLastUsed().getTime() + getTokenValiditySeconds() * 1000L < System
				.currentTimeMillis()) {
			throw new RememberMeAuthenticationException("Remember-me login has expired");
		}

		// Token also matches, so login is valid. Update the token value, keeping the
		// *same* series number.
		if (logger.isDebugEnabled()) {
			logger.debug("Refreshing persistent login token for user '"
					+ token.getUsername() + "', series '" + token.getSeries() + "'");
		}

		PersistentRememberMeToken newToken = new PersistentRememberMeToken(
				token.getUsername(), token.getSeries(), generateTokenValue(), new Date());

		try {
			userService.updateToken(newToken.getSeries(), newToken.getTokenValue(), newToken.getDate());;
			// 쿠키 발행
			String[] rawCookieValues = new String[] { newToken.getSeries(),  newToken.getTokenValue() };
			super.setCookie(rawCookieValues, getTokenValiditySeconds(), request, response);	
			
		}
		catch (Exception e) {
			logger.error("Failed to update token: ", e);
			throw new RememberMeAuthenticationException(
					"Autologin failed due to data access problem");
		}

		return getUserDetailsService().loadUserByUsername(token.getUsername());
	}
	
	@Override
	/* 로그아웃 시 자동 로그인 관련 정보 처리 */
	public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		 
		// DB token 삭제 (username이 아닌 해당 series의 정보만 삭제)
		String decodedCookieValue = super.extractRememberMeCookie(request);
		if (decodedCookieValue != null) {
			String[] cookieTokens = super.decodeCookie(decodedCookieValue);
			if (cookieTokens != null && cookieTokens.length == 2) {
				System.out.println("cookieTokens[0]: "+ cookieTokens[0]);
				userService.removeUserTokens(cookieTokens[0]);
			}
		}

			// 쿠키 삭제
		super.logout(request, response, authentication);
	}
	
	private String generateTokenValue() {
		byte[] newToken = new byte[16];
		random.nextBytes(newToken);
		return new String(Base64.encode(newToken));
	}
	
	public UserDetails test(String[] cookieTokens, HttpServletRequest request,
			HttpServletResponse response) {
		
		return processAutoLoginCookie(cookieTokens, request,response);
	}

}
