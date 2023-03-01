/*
 * @(#)Global.java	2016年11月8日下午6:09:04
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.util;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Properties;

/**
 *
 * 类<strong>Global.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年11月8日 下午6:09:04
 */
@Component
public class Global {

	private static final Logger log = LoggerFactory.getLogger(Global.class);

	private static Properties properties;

	@Resource(name="appProperties")
	public void setAppProperties(Properties appProperties) {
		this.properties = appProperties;
	}

	@PostConstruct
	public void init() {
		log.debug(properties.getProperty("jdbc.url"));
		log.debug(properties.getProperty("jdbc.username"));
		log.debug(properties.getProperty("jdbc.password"));
		log.debug("app.properties = "+properties.toString());
	}


	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();


	public static Properties getProps(){
		return properties;
	}
	/**
	 * 获取配置
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = properties.getProperty(key);
			map.put(key, null != value ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 获得数据库类型
	 * @return
	 */
	public static String getDatabaseType() {
		return getConfig("jdbc.type");
	}

	/**
	 * 获得数据库名
	 * @return
	 */
	public static String getDatabaseName() {
		String jdbcUrl = getConfig("jdbc.url");
		return jdbcUrl.substring(jdbcUrl.lastIndexOf("/") + 1, jdbcUrl.indexOf("?"));
	}

}
