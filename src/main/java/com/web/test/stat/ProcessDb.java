package com.web.test.stat;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessDb {
	@Autowired 
	private statDao dao;
	
	public void processDb() {
		try {
			//dao.insert5MinStat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
