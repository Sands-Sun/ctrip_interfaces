/*
 * @(#)HttpUtils.java	2017-3-30下午01:52:26
 * Copyright 2010 CPIC All rights reserved.
 */
package com.dne.core.util;

import org.springframework.util.Base64Utils;

import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * 
 * 类<strong>HttpUtils.java</strong>{Http调用工具类}
 * @version: 1.0 Date: 2017-3-30下午01:52:26
 */
public class HttpUtils {

	/**
	 * Http调用-GET
	 * @param httpUrl
	 * @param content
	 * @return
	 */
	public static String doGet(String httpUrl, Map<String, Object> reqMap) {
		String rspData = "";
		InputStream is = null;
		if (null != reqMap && !reqMap.isEmpty()) {
			httpUrl = httpUrl + "?" + concatParams(reqMap);
		}
		try {
			HttpURLConnection conn = getHttpConnection(httpUrl, "GET", "application/x-www-form-urlencoded");
			/* 读取返回数据 */
			System.out.println("lvl_conn(get):"+conn);
			int status = conn.getResponseCode();
			System.out.println("lvl_status(get):"+status);
			System.out.println("lvl_message(get):"+conn.getResponseMessage());
			if (status == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
				if (null != is) {
					rspData = readInputStreamData(is);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, null);
		}
		return rspData;
	}

	public static String doPost(String httpUrl, String reqJson) {
		String rspData = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			HttpURLConnection conn = getHttpConnection(httpUrl, "POST", "application/json");
			/* 发送数据 */
			if (null != reqJson && !"".equals(reqJson)) {
				os = conn.getOutputStream();
				os.write(reqJson.getBytes());
				os.flush();
			}
			/* 读取返回数据 */
			int status = conn.getResponseCode();

			if (status == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
				if (null != is) {
					rspData = readInputStreamData(is);
				}
			}else {
				System.out.println("post-response-error-status:"+status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, os);
		}
		return rspData;
	}

	/**
	 * Http调用-POST
	 * @param httpUrl
	 * @param content
	 * @return
	 */
	public static String doPost(String httpUrl, Map<String, Object> reqMap) {
		String rspData = null;
		String content = concatParams(reqMap);

		InputStream is = null;
		OutputStream os = null;
		try {
			HttpURLConnection conn = getHttpConnection(httpUrl, "POST", "application/x-www-form-urlencoded");
			/* 发送数据 */
			if (null != content && !"".equals(content)) {
				os = conn.getOutputStream();
				os.write(content.getBytes());
				os.flush();
			}
			/* 读取返回数据 */
			int status = conn.getResponseCode();
			
			if (status == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
				if (null != is) {
					rspData = readInputStreamData(is);
				}
			}else {
				System.out.println("post-response-error-status:"+status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, os);
		}
		return rspData;
	}

	private static HttpURLConnection getHttpConnection(String httpUrl, String method, String contentType) throws IOException {
		URL url = new URL(httpUrl);
		HttpURLConnection conn = null;
		if (HttpProxyConfig.isEnable()) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HttpProxyConfig.getHost(), HttpProxyConfig.getPort()));
			conn = (HttpURLConnection)url.openConnection(proxy);
			if (StringUtils.isNotEmpty(HttpProxyConfig.getUsername()) && StringUtils.isNotEmpty(HttpProxyConfig.getPassword())) {
				String encode = Base64Utils.encodeToString((HttpProxyConfig.getUsername() + ":" + HttpProxyConfig.getPassword()).getBytes());
				conn.setRequestProperty("Proxy-Authorization", "Basic " + encode);
			}
		} else {
			conn = (HttpURLConnection)url.openConnection();
		}
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(60000);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", contentType);
		conn.connect();
		return conn;
	}

	/**
	 * 设置HTTP URL 参数
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String concatParams(Map<String, Object> reqMap) {
		StringBuilder sb = new StringBuilder();
		try {
			for (Map.Entry<String, Object> map : reqMap.entrySet()) {
				if (null != map.getValue()) {
					sb.append(map.getKey()).append("=").append(URLEncoder.encode(map.getValue().toString(), "utf-8")).append("&");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (sb.length() > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}

	/**
	 * 从输入流中读数据
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private static String readInputStreamData(InputStream is) throws IOException {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream(4096);
		try {
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				outstream.write(buffer, 0, len);
			}
		} finally {
			outstream.close();
		}
		return new String(outstream.toByteArray());
	}

	private static void close(InputStream is, OutputStream os) {
		if (null != os) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (null != is) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
