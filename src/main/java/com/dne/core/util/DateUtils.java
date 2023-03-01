/*
 * @(#)DateUtils.java	2016年8月31日下午1:31:27
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * 类<strong>DateUtils.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年8月31日 下午1:31:27
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils { 

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 获取指定的日期，格式为yyyy-MM-dd
	 * @param amount -1:昨天, 0:今天 ,1:明天
	 * @return
	 */
	public static String getCustomDate(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, amount);
		return String.format("%tY-%<tm-%<td", calendar.getTime());
	}

	/**
	 * 获取指定的日期，格式为yyyy-MM-dd
	 * @param date
	 * @param amount
	 * @return
	 */
	public static String getCustomDate(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return String.format("%tY-%<tm-%<td", calendar.getTime());
	}

	/**
	 * 获取指定的日期，格式为yyyy-MM-dd HH:mm:ss
	 * @param amount -1:昨天, 0:今天 ,1:明天
	 * @return
	 */
	public static String getCustomDateTime(int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, amount);
		return String.format("%tF %<tT", calendar.getTime());
		
	}

	/**
	 * 字符串转化为日期
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String dateStr, String pattern) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符转换为指定日期格式的字符
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String strToDateStr(String dateStr, String format) {
		String strDate = "";
		if (!StringUtils.isEmpty(dateStr)) {
			Date date = strToDate(dateStr);
			strDate = dateToStr(date, format);
		}
		return strDate;
	}

	/**
	 * 日期转换为字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 日期转字符串
	 * @param date
	 * @return
	 */
	public static String dateTimeToStr(Date date) {
		if (null == date) {
			return "";
		}
		return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符转日期
	 * @param dateStr
	 * @return
	 */
	public static Date strToDateTime(String dateStr) {
		return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符转日期
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		int len = dateStr.length();
		SimpleDateFormat dateFormat = null;
		if (len == 8) {
			dateFormat = new SimpleDateFormat("yyyyMMdd");
		} else if (len == 10) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} else if (len == 14) {
			dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		} else if (len == 16) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if (len == 21) {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (dateStr.contains("CST")) {
			dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);// CST格式
		} else if (dateStr.contains("UTC")) {
			dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'UTC 0800' yyyy", Locale.ENGLISH);// UTC格式
		} else {
			return null;
		}
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str, parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long time = new Date().getTime() - date.getTime();
		return time / (60 * 1000);
	}

	/**
	 * 获取上周一日期
	 * @param date
	 * @return
	 */
	public static Date geLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	/**
	 * 获取本周一日期
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 * 获取当前月第一天
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return dateToStr(calendar.getTime(), parsePatterns[0]);
	}

	/**
	 * 获取当前月最后一天
	 * @return
	 */
	public static String getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateToStr(calendar.getTime(), parsePatterns[0]);
	}

	/**
	 * 获取上个月第一天
	 * @return
	 */
	public static String getFirstDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return dateToStr(calendar.getTime(), parsePatterns[0]);
	}

	/**
	 * 获取上个月最后一天
	 * @return
	 */
	public static String getLastDayOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return dateToStr(calendar.getTime(), parsePatterns[0]);
	}
	
	
	/**
	 * 获取上个月的总天数
	 * @return
	 * @param args
	 */
	public static int getLastTotalDayOfLastMonth() {
		long end =strToDate(DateUtils.getLastDayOfLastMonth()).getTime();
		long first   =strToDate(DateUtils.getFirstDayOfLastMonth()).getTime();
		int total =(int) ((end-first)/86400000)+1;
		return total;
	}

	public static void main(String[] args) {
		System.out.println(getLastTotalDayOfLastMonth());
	}
}
