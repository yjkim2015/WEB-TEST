package com.web.test.stat;


import java.util.HashMap;
import java.util.Map;


public class CronWorker extends QueueWorker {
	
	
	private statDao dao;
	
	public CronWorker(String name) {
		super(name);
	}
	
	public CronWorker(String name, statDao dao) {
		super(name);
		this.dao = dao;
	}

	
	@Override
	public void processObject(Object obj) {
		
		try {
			Map<String,String> paramMap = new HashMap<>();
			
			paramMap.put("query", obj.toString());
			
			dao.insertPcInfo(paramMap);

		} catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
