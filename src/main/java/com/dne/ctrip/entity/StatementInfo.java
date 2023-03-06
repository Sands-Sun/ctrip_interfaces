/*
 * @(#)StatementInfo.java	2018年2月5日下午1:30:48
 * Copyright 2017 All rights reserved.
 */
package com.dne.ctrip.entity;

import com.dne.core.basic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 类<strong>StatementInfo.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年2月5日 下午1:30:48
 */
@Getter
@Setter
public class StatementInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer statementId;

	private String statementDate;

	private String statementFile;

	private Integer orderNum;

	private Integer refundNum;

	private String checkStatus;

	private String batchNo;

	public StatementInfo() {

	}

	public StatementInfo(String statementFile, String statementDate, Integer orderNum, Integer refundNum, String checkStatus, String batchNo) {
		this.statementFile = statementFile;
		this.statementDate = statementDate;
		this.orderNum = orderNum;
		this.refundNum = refundNum;
		this.checkStatus = checkStatus;
		this.batchNo = batchNo;
	}


}
