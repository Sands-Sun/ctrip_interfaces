package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
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

}
