package com.quick.yjk.test.reference;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quick.yjk.common.push.PushService;

//스케줄러 클래스
@Component
public class StatScheduler {
	
	
//	
//	//통계 Dao
//	@Autowired
//	private statDao dao;
//
//	@Autowired
//	private PushService pushSerive;
//	
//	
//	//DB작업을 쓰레드로 돌리기 위한 Worker
//	private CronWorker cronWorker;
//	
//	@Scheduled(cron = "*/10 * * * * *")
//	public void process10SecStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_10SCEC, cronWorker);
//		System.out.println("+++ process10secStat start");
//		
//		cron.run();
//		
//	}
//	
//	@Scheduled(cron = "10 0/1 * * * *")
//	public void process1MinStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_1MIN, cronWorker);
//		System.out.println("+++ process1MincStat start");
//		
//		cron.run();
//		
//	}
//	
//	@Scheduled(cron = "0 2,7,12,17,22,27,32,37,42,47,52,57 * * * *")
//	public void process5MinStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_5MIN, cronWorker);
//		System.out.println("+++ process5MinStat start");
//		
//		cron.run();
//		
//	}
//	
//	@Scheduled(cron = "0 6 * * * *")
//	public void process1HStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_1H, cronWorker);
//		System.out.println("+++ process1HStat start");
//		
//		cron.run();
//		
//	}
//	
//	
//	@Scheduled(cron = "0 30 0 * * *")
//	public void processDailyStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_1D, cronWorker);
//		System.out.println("+++ process1DStat start");
//		
//		cron.run();
//	}
//
//	@Scheduled(cron = "0 30 2 1 * *")
//	public void processMonthStat() {
//		CronProcess cron = new CronProcess(TimeType.PT_ST_1M, cronWorker);
//		System.out.println("+++ process1MsecStat start");
//		
//		cron.run();
//	}
//	
//	
//	@Scheduled(cron = "* * * * * *")
//	public void houseKeeper() {
//		
//	}
//	
//	
//	@PostConstruct
//	public void setsProcess() {
//		cronWorker = new CronWorker("statWorker", dao, pushSerive);
//		
//		cronWorker.start();
//	}
}
