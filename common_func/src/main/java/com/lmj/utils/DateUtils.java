package com.lmj.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author 李梦杰
 * @version 2021-3-16
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 日期加减
	 * @param days 间隔天数
	 * @return
	 */
	public static Date addDays(int days) {
		Date todayDate=new Date();    
		long beforeTime=(todayDate.getTime()/1000)+60*60*24*days;    
		todayDate.setTime(beforeTime*1000);    
		return todayDate;
	}
	public static Date addDays(Date date,int days) {
		long beforeTime=(date.getTime()/1000)+60*60*24*days;    
		date.setTime(beforeTime*1000);    
		return date;
	}
	public static Date addHours(int hours) {
		Date todayDate=new Date();    
		long beforeTime=(todayDate.getTime()/1000)+60*60*hours;    
		todayDate.setTime(beforeTime*1000);    
		return todayDate;
	}
	public static Date addMin(int mins) {
		Date todayDate=new Date();    
		long beforeTime=(todayDate.getTime()/1000)+60*mins;    
		todayDate.setTime(beforeTime*1000);    
		return todayDate;
	}
	
	/**
	 * 判断传入时间是否是当天时间
	 * @param date
	 * @author lmj
	 */
	public static boolean isToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar currCalendar = Calendar.getInstance();
		if(calendar.get(Calendar.YEAR) == currCalendar.get(Calendar.YEAR) &&
			calendar.get(Calendar.DAY_OF_YEAR) == currCalendar.get(Calendar.DAY_OF_YEAR)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断传入时间是否为当月时间
	 * @param date
	 * @author lmj
	 */
	public static boolean isCurrentMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar currCalendar = Calendar.getInstance();
		if(calendar.get(Calendar.YEAR) == currCalendar.get(Calendar.YEAR) &&
			calendar.get(Calendar.MONTH) == currCalendar.get(Calendar.MONTH)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断传入时间是否为当年时间
	 * @param date
	 * @author lmj
	 */
	public static boolean isCurrentYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar currCalendar = Calendar.getInstance();
		if(calendar.get(Calendar.YEAR) == currCalendar.get(Calendar.YEAR)){
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		
//		Date beginDate = DateUtils.parseDate("2017-03-17");
//		System.out.println("===="+beginDate.getTime());//1489680000000

	}
	
	
}
