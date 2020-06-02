package com.web.test.stat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.web.test.common.constants.DateUtil;
import com.web.test.common.constants.TimeType;

public class ProcessDateInfo {
	private String timeType;

    public String currentTime;
    public String pid = "";
    public String yymmdd = "";
    public String startTime = "";
    public String endTime = "";
    public String preMonthLastDay = "";
    public String divisionNum = "";
	
    public ProcessDateInfo(String currentTime, String timeType) {
        this.timeType = timeType;
        this.currentTime = currentTime;
        parse();
    }
    
    public void parse() {
    	try {
    		Date currentDate;
    		currentDate = DateUtil.getTime(DateUtil.yyMMddHHmm, currentTime);
    		Calendar calendar = GregorianCalendar.getInstance();
    		calendar.setTime(currentDate);
    		int month = calendar.get(Calendar.MONTH);
    		pid = month + ""; // [ 0 ~ 11 ];
    		
            if(timeType == TimeType.PT_ST_1MIN) {
            	int minute = calendar.get(Calendar.MINUTE);
            	System.out.println("minute : " + minute);
                int convertMinute = (minute / 1) * 1;

                calendar.set(Calendar.MINUTE, convertMinute);
                
                Date yymmddDate = calendar.getTime();
                yymmdd = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, yymmddDate);
                
                endTime = yymmdd;	
                
                System.out.println("convertMinute : " + convertMinute);
                
                calendar.add(Calendar.MINUTE, -1);
                Date startTimeDate = calendar.getTime();

                startTime = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, startTimeDate);

                System.out.println("startTime : "+ startTime);
                System.out.println("endTime : "+  endTime);
                
            }
            else if ( timeType == TimeType.PT_ST_5MIN ) {
            	
            	int minute = calendar.get(Calendar.MINUTE);
            	System.out.println("minute : " + minute);
                int convertMinute = (minute / 5) * 5;

                calendar.set(Calendar.MINUTE, convertMinute);
                
                Date yymmddDate = calendar.getTime();
                yymmdd = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, yymmddDate);
                
                endTime = yymmdd;	
                
                System.out.println("convertMinute : " + convertMinute);
                
                calendar.add(Calendar.MINUTE, -4);
                Date startTimeDate = calendar.getTime();

                startTime = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, startTimeDate);

                System.out.println("startTime : "+ startTime);
                System.out.println("endTime : "+  endTime);
            	
            }
            else if ( timeType == TimeType.PT_ST_1H ) {
            	int hour = calendar.get(Calendar.HOUR);
            	
            	int convertHour = ( hour / 1) * 1;
            	
            	calendar.set(Calendar.HOUR, convertHour);
                Date yymmddDate = calendar.getTime();
                yymmdd = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, yymmddDate);
                endTime = yymmdd;	
                calendar.add(Calendar.HOUR, -1);
                Date startTimeDate = calendar.getTime();
                startTime = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, startTimeDate);
                System.out.println("startTime : "+ startTime);
                System.out.println("endTime : "+  endTime);
            }
            else if ( timeType == TimeType.PT_ST_1D ) {
            	int day = calendar.get(Calendar.DATE);
            	int convertDay = ( day / 1) * 1;

            	calendar.set(Calendar.DATE, convertDay);
                Date yymmddDate = calendar.getTime();
                yymmdd = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, yymmddDate);
                endTime = yymmdd;	
                calendar.add(Calendar.DATE, -1);
                Date startTimeDate = calendar.getTime();
                startTime = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, startTimeDate);
                System.out.println("startTime : "+ startTime);
                System.out.println("endTime : "+  endTime);
                
            }
            else {
            	
            	int convertMonth = ( month / 1) * 1;

            	calendar.set(Calendar.MONTH, convertMonth);
                Date yymmddDate = calendar.getTime();
                yymmdd = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, yymmddDate);
                endTime = yymmdd;	
                calendar.add(Calendar.MONTH, -1);
                Date startTimeDate = calendar.getTime();
                startTime = DateUtil.getTimeStr(DateUtil.yyMMddHHmm, startTimeDate);
                System.out.println("startTime : "+ startTime);
                System.out.println("endTime : "+  endTime);
            }
            
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }
}
