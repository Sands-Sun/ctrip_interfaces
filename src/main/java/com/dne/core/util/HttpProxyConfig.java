/*
 * @(#)HttpProxyConfig.java	2018年1月22日下午1:46:54
 * Copyright 2017 All rights reserved.
 */
package com.dne.core.util;

/**
 * 
 * 类<strong>HttpProxyConfig.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年1月22日 下午1:46:54
 */
public class HttpProxyConfig {

	private static boolean enable = false;

	private static String host;

	private static int port = 8080;

	private static String username;

	private static String password;

	static {
		enable = Boolean.valueOf(Global.getConfig("http.proxy.set"));
		host = Global.getConfig("http.proxy.host");
		username = Global.getConfig("http.proxy.username");
		password = Global.getConfig("http.proxy.password");
		String portStr = Global.getConfig("http.proxy.port");
		if (StringUtils.isNotEmpty(portStr)) {
			port = Integer.parseInt(portStr);
		}
	}

	public static boolean isEnable() {
		return enable;
	}

	public static String getHost() {
		return host;
	}

	public static int getPort() {
		return port;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

}
