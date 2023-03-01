/*
 * @(#)OrderCheckInfo.java	2018年2月7日下午4:30:38
 * Copyright 2017 All rights reserved.
 */
package com.dne.ctrip.finance.entity;

import com.dne.core.basic.entity.BaseEntity;

/**
 * 
 * 类<strong>OrderCheckInfo.java</strong>{此类功能描述}
 * @author: wangyf
 * @version: 1.0 Date: 2018年2月7日 下午4:30:38
 */
public class OrderCheckInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String orderAmt;

	private String payTime;

	private String companyCode;

	private String checkResult;

	private String dummyCostCenter;

	private String checkDesc;
	
	private String costCenter;
	
	private String userCostCenter;
	
	private String companyName;
	
	private String budgetCenterCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getDummyCostCenter() {
		return dummyCostCenter;
	}

	public void setDummyCostCenter(String dummyCostCenter) {
		this.dummyCostCenter = dummyCostCenter;
	}
	
	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBudgetCenterCode() {
		return budgetCenterCode;
	}

	public void setBudgetCenterCode(String budgetCenterCode) {
		this.budgetCenterCode = budgetCenterCode;
	}

	public String getCheckDesc() {
		if ("1".equals(checkResult)) {
			return "订单不存在";
		} else if ("2".equals(checkResult)) {
			return "订单重复";
		} else if ("3".equals(checkResult)) {
			return "订单金额大于同步订单金额";
		}
		return checkDesc;
	}

	public void setCheckDesc(String checkDesc) {
		this.checkDesc = checkDesc;
	}
	
	public String getUserCostCenter() {
		return userCostCenter;
	}

	public void setUserCostCenter(String userCostCenter) {
		this.userCostCenter = userCostCenter;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", orderId=" + orderId + ", orderAmt=" + orderAmt + ", payTime=" + payTime + ", companyCode=" + companyCode + ", checkResult=" + checkResult + ", dummyCostCenter=" + dummyCostCenter + "]";
	}

}
