package com.dne.ctrip.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String orderId;// 订单编号
    private Date createTime;// 叫单时间
    private Integer useCarType;// 用车方式
    private String requireLevel;// 车型
    private String city;// 城市ID
    private String cityName;// 城市名称
    private String startName;// 出发地地址
    private String endName;// 目的地地址
    private Date departureTime;// 出发时间
    private Date finishTime;// 结束计价时间
    private String status;// 订单状态
    private String payType;// PaymentType	支付方式（ACCNT:公司账户CCARD:信用卡CASH:现付）
    private String memberId;// 员工在携程企业的ID CtripCardNo
    private Date payTime;// 支付时间
    private String normalDistance;// 总里程
    private BigDecimal totalPrice;// 总金额  SettlementBaseInfo.RealAmount
    private BigDecimal actualPrice;// 实付金额（总金额-券抵扣金额） SettlementBaseInfo.RealAmountHasPost
    private BigDecimal servicesFee;  //商旅管理服务费
    private BigDecimal postServiceFree; //后收商旅管理服务费 后收商旅管理服务费：指预订过程中不展示，仅在结算数据中体现。
    private BigDecimal expressFee;  //快递费
    private BigDecimal carValueAddFee; //增值费用
    private BigDecimal carAddTaxAmount; //手续费（用车加收税额）
    private BigDecimal penaltyFee; //违约金
    private BigDecimal companyPay;// 公司支付金额 OrderDetail.OrderBaseInfo.AccntAmount
    private BigDecimal personalPay;// 个人支付金额 OrderDetail.OrderBaseInfo.PersonAmount
    private Boolean isInvoice;// 报销开票状态  SettlementBaseInfo.IsChecked
    private String callPhone;// 叫车人手机号
    private String passengerPhone;// 乘车人手机号
    private Date firstCreateDate;
    private Date updateDate;// 修改日期
    private String delFlag;// N正常,Y删除
    private String batchNo;// 同步批次号
    private String remark;//打车备注
    private String companyCode;//公司编码
    private String costCenter;//成本中心
    private String delType; //明细类型(O:出票，R：退票)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUseCarType() {
        return useCarType;
    }

    public void setUseCarType(Integer useCarType) {
        this.useCarType = useCarType;
    }

    public String getRequireLevel() {
        return requireLevel;
    }

    public void setRequireLevel(String requireLevel) {
        this.requireLevel = requireLevel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getNormalDistance() {
        return normalDistance;
    }

    public void setNormalDistance(String normalDistance) {
        this.normalDistance = normalDistance;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getServicesFee() {
        return servicesFee;
    }

    public void setServicesFee(BigDecimal servicesFee) {
        this.servicesFee = servicesFee;
    }

    public BigDecimal getPostServiceFree() {
        return postServiceFree;
    }

    public void setPostServiceFree(BigDecimal postServiceFree) {
        this.postServiceFree = postServiceFree;
    }

    public BigDecimal getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(BigDecimal expressFee) {
        this.expressFee = expressFee;
    }

    public BigDecimal getCarValueAddFee() {
        return carValueAddFee;
    }

    public void setCarValueAddFee(BigDecimal carValueAddFee) {
        this.carValueAddFee = carValueAddFee;
    }

    public BigDecimal getCarAddTaxAmount() {
        return carAddTaxAmount;
    }

    public void setCarAddTaxAmount(BigDecimal carAddTaxAmount) {
        this.carAddTaxAmount = carAddTaxAmount;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public BigDecimal getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(BigDecimal companyPay) {
        this.companyPay = companyPay;
    }

    public BigDecimal getPersonalPay() {
        return personalPay;
    }

    public void setPersonalPay(BigDecimal personalPay) {
        this.personalPay = personalPay;
    }

    public Boolean getInvoice() {
        return isInvoice;
    }

    public void setInvoice(Boolean invoice) {
        isInvoice = invoice;
    }

    public String getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(String callPhone) {
        this.callPhone = callPhone;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public Date getFirstCreateDate() {
        return firstCreateDate;
    }

    public void setFirstCreateDate(Date firstCreateDate) {
        this.firstCreateDate = firstCreateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getDelType() {
        return delType;
    }

    public void setDelType(String delType) {
        this.delType = delType;
    }
}
