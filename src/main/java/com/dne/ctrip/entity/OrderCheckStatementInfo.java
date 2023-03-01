package com.dne.ctrip.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderCheckStatementInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long statementId;
    private String orderId;
    private String orderStatus;
    private String useCarType;
    private String useCarTypeDetailInfo;
    private String callUserName;
    private String callUserEmployeeId;
    private String callUserEmployeeIpin;
    private String emailAddress;
    private String callUserPhone;
    private String departmentName;
    private String departmentCode;
    private String callUserCompanyCode;
    private String passengerName;
    private String passengerPhone;
    private String paymentType;
    private String orderTotalPrice;
    private String companyRealPay;
    private String personalPayment;
    private String serviceContent;
    private String personalPaymentDetail;
    private String carBasicFeeDetail;
    private String servicesFee;
    private String postServiceFree;
    private String expressFee;
    private String carValueAddFee;
    private String carAddTaxAmount;
    private String penaltyFee;
    private String refundPrice;
    private String orderType;
    private Date orderTime;
    private Date planTime;
    private Date receivingTime;
    private Date beginChargeTime;
    private Date endChargeTime;
    private Date payTime;
    private Date refundTime;
    private String callContry;
    private String arriveCity;
    private String driveDistance;
    private String startPlace;
    private String startPlaceName;
    private String destination;
    private String destinationName;
    private String realStartPlace;
    private String realDestination;
    private String useCarRegulation;
    private String useCarMode;
    private String costCenterName;
    private String costCenterId;
    private String useCarRemark;
    private String additionalRemarks;
    private String postingCostCenter;
    private String postingPrice;
    private String statementDate;
    private String batchNo;
    private String ccTag;
    private String companyCode;
    private String costCenter;
    private String checkResult;
    private String markDeleted;
    private Date createDate;
    private Date updateDate;
    private String remark;

    private String department3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUseCarType() {
        return useCarType;
    }

    public void setUseCarType(String useCarType) {
        this.useCarType = useCarType;
    }

    public String getUseCarTypeDetailInfo() {
        return useCarTypeDetailInfo;
    }

    public void setUseCarTypeDetailInfo(String useCarTypeDetailInfo) {
        this.useCarTypeDetailInfo = useCarTypeDetailInfo;
    }

    public String getCallUserName() {
        return callUserName;
    }

    public void setCallUserName(String callUserName) {
        this.callUserName = callUserName;
    }

    public String getCallUserEmployeeId() {
        return callUserEmployeeId;
    }

    public void setCallUserEmployeeId(String callUserEmployeeId) {
        this.callUserEmployeeId = callUserEmployeeId;
    }

    public String getCallUserEmployeeIpin() {
        return callUserEmployeeIpin;
    }

    public void setCallUserEmployeeIpin(String callUserEmployeeIpin) {
        this.callUserEmployeeIpin = callUserEmployeeIpin;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCallUserPhone() {
        return callUserPhone;
    }

    public void setCallUserPhone(String callUserPhone) {
        this.callUserPhone = callUserPhone;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getCallUserCompanyCode() {
        return callUserCompanyCode;
    }

    public void setCallUserCompanyCode(String callUserCompanyCode) {
        this.callUserCompanyCode = callUserCompanyCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getCompanyRealPay() {
        return companyRealPay;
    }

    public void setCompanyRealPay(String companyRealPay) {
        this.companyRealPay = companyRealPay;
    }

    public String getPersonalPayment() {
        return personalPayment;
    }

    public void setPersonalPayment(String personalPayment) {
        this.personalPayment = personalPayment;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getPersonalPaymentDetail() {
        return personalPaymentDetail;
    }

    public void setPersonalPaymentDetail(String personalPaymentDetail) {
        this.personalPaymentDetail = personalPaymentDetail;
    }

    public String getCarBasicFeeDetail() {
        return carBasicFeeDetail;
    }

    public void setCarBasicFeeDetail(String carBasicFeeDetail) {
        this.carBasicFeeDetail = carBasicFeeDetail;
    }

    public String getServicesFee() {
        return servicesFee;
    }

    public void setServicesFee(String servicesFee) {
        this.servicesFee = servicesFee;
    }

    public String getPostServiceFree() {
        return postServiceFree;
    }

    public void setPostServiceFree(String postServiceFree) {
        this.postServiceFree = postServiceFree;
    }

    public String getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(String expressFee) {
        this.expressFee = expressFee;
    }

    public String getCarValueAddFee() {
        return carValueAddFee;
    }

    public void setCarValueAddFee(String carValueAddFee) {
        this.carValueAddFee = carValueAddFee;
    }

    public String getCarAddTaxAmount() {
        return carAddTaxAmount;
    }

    public void setCarAddTaxAmount(String carAddTaxAmount) {
        this.carAddTaxAmount = carAddTaxAmount;
    }

    public String getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(String penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public String getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(String refundPrice) {
        this.refundPrice = refundPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Date getBeginChargeTime() {
        return beginChargeTime;
    }

    public void setBeginChargeTime(Date beginChargeTime) {
        this.beginChargeTime = beginChargeTime;
    }

    public Date getEndChargeTime() {
        return endChargeTime;
    }

    public void setEndChargeTime(Date endChargeTime) {
        this.endChargeTime = endChargeTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getCallContry() {
        return callContry;
    }

    public void setCallContry(String callContry) {
        this.callContry = callContry;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }

    public String getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(String driveDistance) {
        this.driveDistance = driveDistance;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getStartPlaceName() {
        return startPlaceName;
    }

    public void setStartPlaceName(String startPlaceName) {
        this.startPlaceName = startPlaceName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getRealStartPlace() {
        return realStartPlace;
    }

    public void setRealStartPlace(String realStartPlace) {
        this.realStartPlace = realStartPlace;
    }

    public String getRealDestination() {
        return realDestination;
    }

    public void setRealDestination(String realDestination) {
        this.realDestination = realDestination;
    }

    public String getUseCarRegulation() {
        return useCarRegulation;
    }

    public void setUseCarRegulation(String useCarRegulation) {
        this.useCarRegulation = useCarRegulation;
    }

    public String getUseCarMode() {
        return useCarMode;
    }

    public void setUseCarMode(String useCarMode) {
        this.useCarMode = useCarMode;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getUseCarRemark() {
        return useCarRemark;
    }

    public void setUseCarRemark(String useCarRemark) {
        this.useCarRemark = useCarRemark;
    }

    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public void setAdditionalRemarks(String additionalRemarks) {
        this.additionalRemarks = additionalRemarks;
    }

    public String getPostingCostCenter() {
        return postingCostCenter;
    }

    public void setPostingCostCenter(String postingCostCenter) {
        this.postingCostCenter = postingCostCenter;
    }

    public String getPostingPrice() {
        return postingPrice;
    }

    public void setPostingPrice(String postingPrice) {
        this.postingPrice = postingPrice;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCcTag() {
        return ccTag;
    }

    public void setCcTag(String ccTag) {
        this.ccTag = ccTag;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getMarkDeleted() {
        return markDeleted;
    }

    public void setMarkDeleted(String markDeleted) {
        this.markDeleted = markDeleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepartment3() {
        return department3;
    }

    public void setDepartment3(String department3) {
        this.department3 = department3;
    }
}
