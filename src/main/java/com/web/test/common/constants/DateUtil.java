package com.web.test.common.constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.expression.ParseException;

public class DateUtil {
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
	
	
    public static final String yyMMddHH = "yyyy-MM-dd-HH";
	
    
    public static final String yyMMddHHmm = "yyyy-MM-dd HH:mm";

    
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
	
    public static String getCurrentDateString(String format)
    {
        SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
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
	
	 public static Date getTime(String format, String value) throws Exception
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(value);
    }
	/**
	 * 날짜 형식 변환
	 * @param yyyyMMddHHmm
	 * @return
	 * @throws ParseException
	 */
	public static String convertDateFormat(final String yyyyMMddHHmm) throws ParseException {
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREAN);
		Date date = null;
		try {
			date = sdf.parse(yyyyMMddHHmm);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sdf						= new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREAN);
		
		return sdf.format(date);
	}
	
	public static String getTimeStr(String format, Date value) throws Exception
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String yymmdd = "";
        try
        {
            yymmdd = simpleDateFormat.format(value);
        }
        catch(Exception e)
        {

        }

        return yymmdd;
    }
}

