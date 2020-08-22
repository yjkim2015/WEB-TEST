package com.quick.yjk.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 날짜 관련 Util 클래스
 * @author 배수현
 *
 */
public final class DateUtil {
	/**
	 * 일시 포맷
	 */
	public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREAN); 
	
	/**
	 * 파일명을 위한 일시 포맷
	 */
	public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREAN);
	
	/**
	 * 파일명을 위한 일자 포맷
	 */
	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 생성자
	 */
	private DateUtil() {
		super();
	}
	
	/**
	 * 현재 일시
	 * @return
	 */
	public static String currentDateTime() {
		synchronized (DATETIME_FORMAT) {
			return DATETIME_FORMAT.format(Calendar.getInstance().getTime());
		}
	}
	
	/**
	 * 파일명 생성을 위한 현재 일시
	 * @return
	 */
	public static String currentDateTime2() {
		synchronized (YYYYMMDDHHMMSS_FORMAT) {
			return YYYYMMDDHHMMSS_FORMAT.format(Calendar.getInstance().getTime());
		}
	}
	
	/**
	 * 며칠 전 일시
	 * @param days
	 * @return
	 */
	public static String daysAgo(final int days) {
		synchronized (DATETIME_FORMAT) {
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1 * days);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			return DATETIME_FORMAT.format(cal.getTime());
		}		
	}

	/**
	 * 감사 로그 발생 일시
	 * @return
	 */
	public static String getAuditLogEventDate() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREAN);
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * unix 시각을 timestamp로 변환
	 * @param unixtime
	 * @return
	 */
	public static String convertUnixtimeToTimestamp(final String unixtime) {
		final long timestamp 		= Long.parseLong(String.format("%s%s", unixtime, "000"));
		final SimpleDateFormat sdf 	= new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
		
		return sdf.format(new Date(timestamp));
	}
	
	/**
	 * 날짜 형식 변환
	 * @param yyyyMMddHHmm
	 * @return
	 * @throws ParseException
	 */
	public static String convertDateFormat(final String yyyyMMddHHmm) throws ParseException {
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREAN);
		final Date date 		= sdf.parse(yyyyMMddHHmm);
		sdf						= new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
		
		return sdf.format(date);
	}
}
