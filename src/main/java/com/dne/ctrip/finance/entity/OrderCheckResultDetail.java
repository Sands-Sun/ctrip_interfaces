package com.dne.ctrip.finance.entity;

import com.dne.core.basic.entity.BaseEntity;

import java.io.Serializable;

public class OrderCheckResultDetail extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private String orderAmt;
    private String payTime;
    private String companyCode;
    private String checkResult;
    private String checkDesc;
    private String costCenter;
    private String userCostCenter;
//    private String dummyCostCenter;

    private String companyNameCn;
    private String companyNameEn;

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

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getUserCostCenter() {
        return userCostCenter;
    }

    public void setUserCostCenter(String userCostCenter) {
        this.userCostCenter = userCostCenter;
    }

    public String getCompanyNameCn() {
        return companyNameCn;
    }

    public void setCompanyNameCn(String companyNameCn) {
        this.companyNameCn = companyNameCn;
    }

    public String getCompanyNameEn() {
        return companyNameEn;
    }

    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    @Override
    public String toString() {
        return "OrderCheckResultDetail{" +
                "orderId='" + orderId + '\'' +
                ", orderAmt='" + orderAmt + '\'' +
                ", payTime='" + payTime + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", checkDesc='" + checkDesc + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", userCostCenter='" + userCostCenter + '\'' +
                ", companyNameCn='" + companyNameCn + '\'' +
                ", companyNameEn='" + companyNameEn + '\'' +
                '}';
    }
}
