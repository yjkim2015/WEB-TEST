package com.quick.yjk.accounting.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.common.component.CommonController;

@Controller
public class SignController extends CommonController{

    private final transient UserService userService;
	
    @Autowired
    public SignController(UserService userService) {
    	super();
    	this.userService = userService;
    }
    
    @RequestMapping(value="/sign", method = RequestMethod.GET)
    public String sign() {
    	
    	return "sign/index";
    }
}
