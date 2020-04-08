package com.web.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.test.common.constants.PushVo;
import com.web.test.common.constants.ResultVo;
import com.web.test.common.push.PushService;

@Controller
public class helloController {

	
	@Autowired
	private PushService pushService;
	
	@RequestMapping(value="/hello")
	public String hell() {
		return "hello";
	}
	public static Map<String,String> sessionList = new HashMap<>();

	@RequestMapping(value="/connect", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultVo> connect(@RequestBody Map<String,Object> map, HttpServletRequest request) {
		System.out.println(map);
		System.out.println("sessionId : " + request.getSession());
    	final ResultVo resultVo = new ResultVo(HttpStatus.OK);

		if ( sessionList.get(map.get("id").toString()) == null ) {
			sessionList.put(map.get("id").toString(),request.getSession().toString());
			PushVo pushVo = new PushVo();
			pushVo.setPayload(map.get("id")+" 님이 접속하셨습니다.");
			pushService.push(pushVo);
		}
		
		return resultVo.build();
	}
	
	@RequestMapping(value="/disconnect", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResultVo> disconnect(@RequestBody Map<String,Object> map, HttpServletRequest request) {
		final ResultVo resultVo = new ResultVo(HttpStatus.OK);
		
		if ( sessionList.get(map.get("id").toString()) != null ) {
			sessionList.remove(map.get("id").toString());
			PushVo pushVo = new PushVo();
			pushVo.setPayload(map.get("id")+" 님이 로그아웃하셨습니다.");
			pushService.push(pushVo);
		}
		
		return resultVo.build();
	}
	
}

