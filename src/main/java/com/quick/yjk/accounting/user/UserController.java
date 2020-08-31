package com.quick.yjk.accounting.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.yjk.common.component.CommonController;
import com.quick.yjk.vo.ResultVo;
import com.quick.yjk.vo.UserVo;

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
    
    @ResponseBody
    @RequestMapping(value="/selectUserList", method = RequestMethod.POST)
    public ResponseEntity<ResultVo> selectUserList(@RequestBody UserVo userVo) {
    	
    	ResultVo result = null;
    	try {
    		result = new ResultVo(userService.selectUserList(userVo), HttpStatus.OK);
    	}
    	catch (Exception e) {
    		result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
    		result.setReason(e.getMessage());
    	}
    	
    	return result.build();
    }
   
    @ResponseBody
    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public ResponseEntity<ResultVo> updateUser(@RequestBody UserVo userVo) {
    	
    	ResultVo result = null;
    	try {
    		result = new ResultVo(userService.updateUser(userVo), HttpStatus.OK);
    	}
    	catch (Exception e) {
    		result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
    		result.setReason(e.getMessage());
    	}
    	
    	return result.build();
    }
   
   
}