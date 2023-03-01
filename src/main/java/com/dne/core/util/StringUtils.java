/*
 * @(#)StringUtils.java	2016年11月7日下午4:04:07
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 类<strong>StringUtils.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年11月7日 下午4:04:07
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


	private static final byte[] UTF_BYTES = {(byte) 0xC2,(byte) 0xA0};
	private static  String UTF_SPACE = new String(UTF_BYTES, StandardCharsets.UTF_8);

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str.toLowerCase());
	}

	/**
	 * 判断字符串不为为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 把字符串转化为整数
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		if (isEmpty(str)) {
			return 0;
		} else {
			if (str.indexOf(".") != -1) {
				str = str.substring(0, str.indexOf("."));
			}
			return Integer.parseInt(str);
		}
	}

	/**
	 * 把字符串转化为Double
	 * @param str
	 * @return
	 */
	public static double strToDouble(String str) {
		if (isEmpty(str)) {
			return 0.00d;
		} else {
			return Double.parseDouble(str);
		}
	}

	/**
	 * 判断字符串是否是数字类型，不带小数点
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断字符串是否是数字类型，带小数点
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否是手机格式
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		Pattern p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 获得32位UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 格式化字符串，长度为num，不够的补0
	 * @param str
	 * @param len
	 * @return
	 */
	public static String getFormatStr(String str, int len) {
		if (isEmpty(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < (len - str.length()); i++) {
			sb.append("0");
		}
		sb.append(str);
		return sb.toString();
	}

	public static String getFormatStr(Number number, int len) {
		return String.format("%0" + len + "d", number);
	}

	/**
	 * 格式化数据
	 * @param data
	 * @param digits
	 * @return
	 */
	public static String formatNumberData(Number data, int digits) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumFractionDigits(digits);
		nf.setMaximumFractionDigits(digits);
		return nf.format(data);
	}

	/**
	 * 获得数字随机数
	 * @param digit
	 * @return
	 */
	public static String getDigitalRandom(int digit) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();// 随机产生的认证码()
		for (int i = 0; i < digit; i++) {
			buffer.append(random.nextInt(10));
		}
		return buffer.toString();
	}

	public static String replaceArray(Object[] objs) {
		if (null == objs || objs.length == 0) {
			return "";
		}
		if (objs[0] instanceof String) {
			return "'" + Joiner.on("','").join(objs) + "'";
		} else {
			return Joiner.on(",").join(objs);
		}
	}

	public static String replaceSrting(Collection<String> col) {
		if (null == col || col.isEmpty()) {
			return "";
		}
		return "'" + Joiner.on("','").join(col) + "'";
	}

	public static String replaceNumber(Collection<Number> col) {
		if (null == col || col.isEmpty()) {
			return "";
		}
		return Joiner.on(",").join(col);
	}

	/**
	 * 字符串分隔
	 * @param str
	 * @param separator
	 * @return
	 */
	public static List<String> splitToList(String str, String separator) {
		return Splitter.on(separator).omitEmptyStrings().trimResults().splitToList(str);
	}

	public static String getString(Object obj) {
		return null == obj ? EMPTY : obj.toString();
	}

	public static int getInt(Object obj) {
		return null == obj ? 0 : Integer.parseInt(obj.toString());
	}

	public static String getNumberFromString(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll(EMPTY).trim();
	}

	public static String replaceUTFWhiteSpace(String str) {
		Pattern p = Pattern.compile("\t|\r|\n|" + UTF_SPACE);
		Matcher m = p.matcher(str);
		return m.replaceAll(EMPTY).trim();
	}


	public static boolean containsChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}
}
