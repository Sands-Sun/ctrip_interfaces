/*
 * @(#)BaseEntity.java	2016年8月31日上午11:15:21
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类<strong>BaseEntity.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年8月31日 上午11:15:21
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEL_FALSE = "0";

	public static final String DEL_TRUE = "1";

	protected Long id;// 主键

	protected String delFlag;// 0正常,1删除

	protected String createBy;// 创建者

	protected Date createDate;// 创建日期

	protected String updateBy;// 修改者

	protected Date updateDate;// 修改日期

	public BaseEntity() {
		super();
		this.delFlag = DEL_FALSE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity other = (BaseEntity)obj;
		return null == this.getId() ? false : this.getId().equals(other.getId());
	}

}
