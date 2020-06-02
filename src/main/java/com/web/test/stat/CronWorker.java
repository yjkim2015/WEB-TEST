package com.web.test.stat;


import java.util.HashMap;
import java.util.Map;

import com.web.test.common.constants.PushVo;
import com.web.test.common.push.PushService;


public class CronWorker extends QueueWorker {
	
	
	private statDao dao;
	
	private PushService pushService;
	
	public CronWorker(String name) {
		super(name);
	}
	
	public CronWorker(String name, statDao dao, PushService pushService) {
		super(name);
		this.dao = dao;
		this.pushService = pushService;
	}

	
	@Override
	public void processObject(Object obj) {
		
		try {
			
			if ( obj instanceof PushVo ) {
				PushVo pushVo = (PushVo) obj;
				Map<String,String> paramMap = new HashMap<>();
				
				paramMap.put("query", pushVo.getQuery());
				
				dao.insertPcInfo(paramMap);
				
				
				pushService.push(pushVo);
			}
			
			
		} catch (Exception e) {

		}
		
	}

}
