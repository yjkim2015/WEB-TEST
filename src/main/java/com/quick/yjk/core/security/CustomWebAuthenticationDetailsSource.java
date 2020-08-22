package com.quick.yjk.core.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * CustomWebAuthenticationDetailsSource
 * @author 배수현
 *
 */
public class CustomWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
  
	@Override
    public WebAuthenticationDetails buildDetails(final HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context);
    }
}
