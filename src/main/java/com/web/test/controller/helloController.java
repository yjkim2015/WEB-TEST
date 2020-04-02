package com.web.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloController {

	@RequestMapping(value="/hello")
	public String hell() {
		System.out.println("hi");
		return "hello";
	}
}

