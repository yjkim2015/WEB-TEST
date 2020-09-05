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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.common.utils.EncryptionUtil;
import com.quick.yjk.vo.UserVo;

public class CustomAuthenticationProvider implements AuthenticationProvider{
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
	
	@Autowired
	private UserDetailsService userDetailsService;
	/**
	 * 로그인 요청을 판단하여 결과를 반환
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String loginId 	= authentication.getName();
		String passwd 	= (String) authentication.getCredentials();
		final CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String username = (String) authentication.getPrincipal();

		UserVo user = (UserVo) userDetailsService.loadUserByUsername(username);
	        
        try {
			if(!matchPassword(EncryptionUtil.encryptSHA512(passwd), user.getPasswd())) {
				LOGGER.debug("비번 다름");
			    throw new BadCredentialsException(username);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
//        if(!user.isEnabled()) {
//            throw new BadCredentialsException(username);
//        }
        
        final List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginId, passwd, roles);
		result.setDetails(user);
		return result;
//	    String username = (String) authentication.getPrincipal();
//		try {
//			UserVo user = (UserVo) userDetailsService.loadUserByUsername(loginId);
//			userVo = new UserVo();
//			userVo.setLoginId(loginId);
//			userVo.setPasswd(EncryptionUtil.encryptSHA512(passwd));
//			userVo = userService.goLogin(userVo);
//			if ( userVo == null ) {
//				throw new BadCredentialsException("no_user");
//			}
//		}
//		catch (NoSuchAlgorithmException nsae) {
//			throw new BadCredentialsException(nsae.getMessage(), nsae);
//		}
//		
//		if (userVo == null) {
//			throw new BadCredentialsException("no_user");
//		}
//		else {
//			final List<GrantedAuthority> roles = new ArrayList<>();
//			roles.add(new SimpleGrantedAuthority("ROLE_"+userVo.getRole()));
//
//			final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginId, passwd, roles);
//			result.setDetails(userVo);
//			return result;
//		}
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
    private boolean matchPassword(String loginPwd, String password) {
        return loginPwd.equals(password);
    }


	
}
