package com.softline.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * <li>日期工具类</li>
 */
public class DateUtils {
	
	
	/**
	 * <li>参数数组</li><br/>
	 * 0:yyyyMMdd<br/>1:yyyy-MM-dd<br/>2:yyyy-MM-dd HH:mm<br/>3:yyyy-MM-dd HH:mm:ss<br/>4:yyyy/MM/dd<br/>
	 * 5:yyyy/MM/dd HH:mm<br/>6:yyyy/MM/dd HH:mm:ss<br/>7:HH:mm:ss[时间]<br/>8:yyyy[年份]<br/>9:MM[月份]<br/>
	 * 10.dd[天]<br/>11.E[星期几]<br/>
	 */
	public static String[] parsePatterns = {"yyyyMMdd","yyyy-MM-dd","yyyy-MM-dd HH:mm","yyyy-MM-dd HH:mm:ss", 
		"yyyy/MM/dd","yyyy/MM/dd HH:mm","yyyy/MM/dd HH:mm:ss","HH:mm:ss","yyyy","MM","dd","E"};

	/**
	 * <li>获取过去的天数</li>
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * <li>按输入日期加分钟</li>
	 * @param date   日期
	 * @param recordPeriodMinute 分钟数
	 * @return
	 */
	public static Date addMinutes(Date recordTime, int recordPeriodMinute) {
		Calendar c = Calendar.getInstance();
		c.setTime(recordTime);
		c.add(Calendar.MINUTE, recordPeriodMinute);
		return c.getTime();
	}
	/**
	 * <li>按输入日期加毫秒</li>
	 * @param date   日期
	 * @param recordPeriodSecond 毫秒数
	 * @return
	 */
	public static Date addSeconds(Date recordTime, int recordPeriodSecond) {
		Calendar c = Calendar.getInstance();
		c.setTime(recordTime);
		c.add(Calendar.SECOND, recordPeriodSecond);
		return c.getTime();
	}
	/**
	 * <li>校验日期是否合法</li>
	 * @param date 日期
	 * @return
	 */
	public static boolean isValidDate(String date) {
		DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
		try {
			fmt.parse(date);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}	
	  /**
     * <li>时间相减得到相差年数</li>
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数</li>
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static long getDaySub(String startTime,String endTime){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat(parsePatterns[1]);
        Date beginDate = null;
        Date endDate = null;   
            try {
				beginDate = format.parse(startTime);
				endDate= format.parse(endTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);   
        return day;
    }

	/**
     * <li>功能描述：获取当前周数</li>
     * @param date 日期
     * @return
     */
    public static Integer getWeekSub(String date){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat(parsePatterns[1]);
        Date beginDate = null;
        Date endDate = null;   
            try {
				beginDate = format.parse(date.substring(0, 4) + "-01-01");
				endDate= format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);   
        return Integer.valueOf((int)(day/7+1));
    }
    /**
     * <li>得到n天之后的日期</li>
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days); 	
        Calendar canlendar = Calendar.getInstance(); 
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat((parsePatterns[3]));
        String dateStr = sdfd.format(date);     
        return dateStr;
    }
    
    /**
     * <li>得到n天之后是周几</li>
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);	
        Calendar canlendar = Calendar.getInstance(); 
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();     
        SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns[11]);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /** 
     * 将微信消息中的CreateTime转换成标准格式的时间Date
     * @param createTime 消息创建时间 
     * @return 
     */  
    public static Date formatTime(long createTime) {  
    	long msgCreateTime =createTime*1000L;  
        return new Date(msgCreateTime);  
    }  

    /**
     * <li>得到当天是周几</li>
     * @return
     */
    public static int getCurrentDayWeek() {
        Calendar canlendar = Calendar.getInstance(); 
        Date date = new Date();     
        canlendar.setTime(date);
        int w = canlendar.get(Calendar.DAY_OF_WEEK);
        return w;
    }
    
    /**
     * 获取当前时间所在年的周数
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
 
        return c.get(Calendar.WEEK_OF_YEAR);
    }
 
    /**
     * 获取当前时间所在年的最大周数
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }
    
    /**
     * 获取某年的第几周的开始日期
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
 
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
 
        return getFirstDayOfWeek(cal.getTime());
    }
 
    /**
     * 获取某年的第几周的结束日期
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
 
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);
 
        return getLastDayOfWeek(cal.getTime());
    }
 
    /**
     *  获取当前时间所在周的开始日期
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }
 
    /**
     * 获取当前时间所在周的结束日期
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }
}
