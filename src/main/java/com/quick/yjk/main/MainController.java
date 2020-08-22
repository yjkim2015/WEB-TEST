package com.quick.yjk.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.yjk.common.component.CommonController;

@Controller
public class MainController extends CommonController {

	@RequestMapping(value="/main", method = RequestMethod.GET) 
	public String main() {
		
		return "main/index";
	}
}
