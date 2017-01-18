package com.mage.platform.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author add by pzh
 */
public class DateUtil {
	/**
	 * 将一个字符串转换成日期格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}

	
	
	/**
	 * 获取上个月的开始结束时间
	 * @return
	 */
	public static String[] getLastMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH) + 1;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";

		   if (month > 1) {
		    month--;
		   } else {
		    year--;
		    month = 12;
		   }
		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] lastMonth = new String[2];
		   lastMonth[0] = firstDay;
		   lastMonth[1] = lastDay;
		   return lastMonth;
		}
	
	
	/**
	 * 获取当月的开始结束时间
	 * @return
	 */
	public static String[] getCurrentMonth() {
		   // 取得系统当前时间
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month = cal.get(Calendar.MONTH)+1 ;
		   
		   // 取得系统当前时间所在月第一天时间对象
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   
		   // 日期减一,取得上月最后一天时间对象
		   cal.add(Calendar.DAY_OF_MONTH, -1);
		   
		   // 输出上月最后一天日期
		   int day = cal.get(Calendar.DAY_OF_MONTH);

		   String months = "";
		   String days = "";


		   if (!(String.valueOf(month).length() > 1)) {
		    months = "0" + month;
		   } else {
		    months = String.valueOf(month);
		   }
		   if (!(String.valueOf(day).length() > 1)) {
		    days = "0" + day;
		   } else {
		    days = String.valueOf(day);
		   }
		   String firstDay = "" + year + "-" + months + "-01";
		   String lastDay = "" + year + "-" + months + "-" + days;

		   String[] currentMonth = new String[2];
		   currentMonth[0] = firstDay;
		   currentMonth[1] = lastDay;
		   return currentMonth;
		}
		
	
	
	public static int getDateline(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	public static int getDateline(String date){
		return (int)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	
	//int最大值反应到日期上为2038-01-19，故补充以下两个方法
	public static long getDatelineLong(){
		
		return (long)(System.currentTimeMillis()/1000);
	}
	
	public static long getDatelineLong(String date){
		return (long)(toDate(date, "yyyy-MM-dd").getTime()/1000);
	}
	
	public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
	public static final int TRUNCTO_YEAR = 0;
	public static final int TRUNCTO_MONTH = 1;
	public static final int TRUNCTO_DAY = 2;

	public static String current(String pattern) {
		return formatDate(current(), pattern);
	}

	public static Date current() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static Date currentDate() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		return date;
	}

	public static Timestamp currentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp addDay(Timestamp time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		c.add(6, days);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp addDay(Date time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		c.add(6, days);
		return new Timestamp(c.getTimeInMillis());
	}
	public static Timestamp trunc(Timestamp time, int truncTo) {

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		int year = 0, month = 0, date = 1, hour = 0, minute = 0, second = 0;
		if (truncTo >= TRUNCTO_YEAR) {
			year = c.get(Calendar.YEAR);
		}
		if (truncTo >= TRUNCTO_MONTH) {
			month = c.get(Calendar.MONTH);
		}
		if (truncTo >= TRUNCTO_DAY) {
			date = c.get(Calendar.DAY_OF_MONTH);
		}
		c.set(year, month, date, hour, minute, second);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static java.sql.Date addDay(java.sql.Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(6, days);
		return new java.sql.Date(c.getTimeInMillis());
	}

	public static Date add(Date date, int filed, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(filed, amount);
		return new Date(c.getTimeInMillis());
	}

	public static Calendar toCalander(Timestamp timestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestamp.getTime());
		return c;
	}
	public static Calendar toCalander(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		return c;
	}
	/**
	 * format date use default pattern: yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return 
	 */
	public static String formatDate(Date date) {
		return formatDate(date, defaultPattern);
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.format(date);
		}
	}

	public static Date parse(String dateStr, Class resultClass) throws Exception {
		return parse(dateStr, defaultPattern, resultClass);
	}

	public static Date parseAsDate(String dateStr) {
		return parse(dateStr, defaultPattern, java.sql.Date.class);
	}
	public static Date parseAsDate(String dateStr,String pattern) {
		return parse(dateStr, pattern, java.sql.Date.class);
	}
	public static Date parseAsTimestamp(String dateStr) {
		return parse(dateStr, defaultPattern, java.sql.Timestamp.class);
	}

	public static Date parse(String dateStr, String pattern, Class<?> resultClass) {
		if (dateStr == null)
			throw new IllegalArgumentException("dateStr is null");
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		} else {

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date date;
			try {
				date = formatter.parse(dateStr);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			if (resultClass == null)
				return date;
			else if (resultClass.equals(java.sql.Date.class)) {
				return new java.sql.Date(date.getTime());
			} else if (resultClass.equals(java.sql.Timestamp.class)) {
				return new java.sql.Timestamp(date.getTime());
			} else {
				throw new IllegalArgumentException(
					"result class must be java.sql.Date or java.sql.Timestamp");
			}
		}
	}

	public static Date addMonth(int amount) {
		return add(currentTime(), Calendar.MONTH, amount);
	}

	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
}
