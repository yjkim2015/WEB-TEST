package com.quick.yjk.accounting.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quick.yjk.common.component.CommonController;

/**
 * 운용자 관리 Controller
 * @author 배수현
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends CommonController {
	/**
	 * 운용자 관리 Service
	 */
    private final transient UserService userService;
    
    /**
     * 생성자 
     * @param userService
     */
    @Autowired
    public UserController(final UserService userService) {
    	super();
    	
    	this.userService = userService;
    }
   
    
    /**
     * 운용자 리스트 조회
     * @param userVo
     * @return
     */
   
    
    /**
     * 계정 패스워드 변경
     * @param userVo
     * @param request
     * @return
     */
   
}