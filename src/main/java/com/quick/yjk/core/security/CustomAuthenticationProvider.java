package com.quick.yjk.core.security;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.common.utils.EncryptionUtil;
import com.quick.yjk.vo.UserVo;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	/**
	 * 인증타입(Local)
	 */
	private static final String LOGIN_TYPE_LOC = "LOC";
	
	@Autowired
	private transient UserService userService;
	
	/**
	 * 로그인 요청을 판단하여 결과를 반환
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String loginId 	= authentication.getName();
		final String passwd 	= (String) authentication.getCredentials();
		final CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
		
		UserVo userVo = null;
		try {
			userVo = new UserVo();
			userVo.setLoginId(loginId);
			userVo.setPasswd(EncryptionUtil.encryptSHA512(passwd));
			userVo = userService.goLogin(userVo);
			if ( userVo == null ) {
				throw new BadCredentialsException("no_user");
			}
			userVo.setLoginType(details.getLoginType());
			
		}
		catch (NoSuchAlgorithmException nsae) {
			throw new BadCredentialsException(nsae.getMessage(), nsae);
		}
		
		if (userVo == null) {
			throw new BadCredentialsException("no_user");
		}
		else {
			final List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));

			final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginId, passwd, roles);
			result.setDetails(userVo);
			return result;
		}
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
