package com.quick.yjk.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.vo.UserVo;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    System.out.println("여기 제일먼저옴");
        UserVo user = new UserVo();
        user.setLoginId(username);
        user = userService.selectOneUser(user);
        if(user==null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}