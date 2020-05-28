package com.web.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CommonController {

	@ModelAttribute("getContextPath")
	public String getContextPath(final HttpServletRequest request) {
		return request.getContextPath();
	}
	
}
