package com.quick.yjk.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.vo.UserVo;

public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;
	
	public static int count = 0;
	

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   CustomUserDetailsService.count++;	
	   LOGGER.debug("loadUserByUsername :" + CustomUserDetailsService.count);
        UserVo user = new UserVo();
        user.setLoginId(username);
        user = userService.selectOneUser(user);
        if(user==null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}