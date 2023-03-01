/*
 * @(#)StatementInfo.java	2018年2月5日下午1:30:48
 * Copyright 2017 All rights reserved.
 */
package com.dne.ctrip.entity;

import com.dne.core.basic.entity.BaseEntity;

/**
 * 
 * 类<strong>StatementInfo.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年2月5日 下午1:30:48
 */
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

	public Integer getStatementId() {
		return statementId;
	}

	public void setStatementId(Integer statementId) {
		this.statementId = statementId;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getStatementFile() {
		return statementFile;
	}

	public void setStatementFile(String statementFile) {
		this.statementFile = statementFile;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

}
