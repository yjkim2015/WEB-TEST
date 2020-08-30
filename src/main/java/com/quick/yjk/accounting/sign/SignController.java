package com.quick.yjk.accounting.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.common.component.CommonController;
import com.quick.yjk.vo.ResultVo;
import com.quick.yjk.vo.UserVo;

@Controller
@RequestMapping("/sign")
public class SignController extends CommonController{

    private final transient UserService userService;
	
    @Autowired
    public SignController(UserService userService) {
    	super();
    	this.userService = userService;
    }
    
    @RequestMapping(value="", method = RequestMethod.GET)
    public String sign() {
    	
    	return "sign/index";
    }
    
    @ResponseBody
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<ResultVo> signup(@RequestBody UserVo userVo) {
    	
    	ResultVo result = null;
    	try {
    		System.out.println(userVo.toString());
    		result = new ResultVo(userService.inserUser(userVo), HttpStatus.OK);
    	}
    	catch (Exception e) {
    		result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
    		result.setReason(e.getMessage());	
    		
    	}
    	
    	
    	return result.build();
    }
    
    @ResponseBody
    @RequestMapping(value="/idCheck", method = RequestMethod.POST)
    public ResponseEntity<ResultVo> idCheck(@RequestBody final UserVo userVo) {
    	
    	ResultVo result = null;
    	System.out.println(userVo.toString());
    	
    	try {
    		result = new ResultVo(userService.checkUser(userVo),HttpStatus.OK);
    	}
    	catch (Exception e) {
    		result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
    		result.setReason(e.getMessage());
    	}
    	
    	
    	return result.build();
    }
}
