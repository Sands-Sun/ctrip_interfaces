/*
 * @(#)BaseEntity.java	2016年8月31日上午11:15:21
 * Copyright 2016 DNE All rights reserved.
 */
package com.dne.core.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 类<strong>BaseEntity.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2016年8月31日 上午11:15:21
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DEL_FALSE = "0";

	public static final String DEL_TRUE = "1";

	protected Long id;// 主键

	protected String delFlag;// 0正常,1删除

	protected String createBy;// 创建者

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date createDate;// 创建日期

	protected String updateBy;// 修改者

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	protected Date updateDate;// 修改日期

	public BaseEntity() {
		super();
		this.delFlag = DEL_FALSE;
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
