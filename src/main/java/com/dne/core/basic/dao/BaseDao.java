/*
 * @(#)BaseDao.java	2016年8月31日上午11:59:42
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.basic.dao;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * 类<strong>BaseDao.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年8月31日 上午11:59:42
 */
public interface BaseDao {

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public <T> T get(Serializable id);

	/**
	 * 根据实体对象查询
	 * @param entity
	 * @return
	 */
	public <T> T get(T entity);

	/**
	 * 根据Map参数查询
	 * @param paramMap
	 * @return
	 */
	public <T> T get(@Param("paramMap") Map<String, Object> paramMap);

	/**
	 * 根据实体名称或字段名称和字段值查询
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findByProperty(@Param(value = "propertyName") String propertyName, @Param(value = "value") Object value);

	/**
	 * 查询所有数据列表
	 * @return
	 */
	public <T> List<T> findAllList();

	/**
	 * 根据实体查询数据列表
	 * @param entity
	 * @return
	 */
	public <T> List<T> findList(T entity);

	/**
	 * 根据Map参数查询
	 * @param paramMap
	 * @return
	 */
	public <T> List<T> findList(@Param("paramMap") Map<String, Object> paramMap);

	/**
	 * 根据字段名称和字段值查询
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> List<T> findListByProperty(@Param(value = "propertyName") String propertyName, @Param(value = "value") Object value);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public <T> int insert(T entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public <T> int update(T entity);

	/**
	 * 删除数据
	 * @param entity
	 * @return
	 */
	public int delete(Serializable id);

	/**
	 * 逻辑删除数据
	 * @param entity
	 * @return
	 */
	public <T> int deleteByLogic(T entity);

	/**
	 * 批量删除数据
	 * @param list
	 * @return
	 */
	public void deleteAll(List<?> list);

}
