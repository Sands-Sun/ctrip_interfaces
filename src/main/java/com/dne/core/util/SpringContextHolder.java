/*
 * @(#)SpringContextHolder.java	2016年8月31日上午10:49:42
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * 类<strong>SpringContextHolder.java</strong>{Spring Bean 工具类}
 * @author: wangyf
 * @version: 1.0 Date: 2016年8月31日 上午10:49:42
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringContextHolder.context = context;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T)context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return (T)context.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return context.containsBean(name);
	}

	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return context.isSingleton(name);
	}

	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return context.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return context.getAliases(name);
	}

	@Override
	public void destroy() throws Exception {
		context = null;
	}

}
