package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarOrderSettlementBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "RecordId")
    private String recordId; //结算明细ID，与OrderType字段一起共同确保数据唯一性，且仅针对当前产品类型唯一
    @JSONField(name = "BatchNo")
    private String batchNo; //结算批次号
    @JSONField(name = "CreateTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //结算生成时间
    @JSONField(name = "DataChangeLastTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date dataChangeLastTime;//结算最晚更新时间
    @JSONField(name = "ProductType")
    private Integer productType; //结算订单产品类型1:国内租车2:国内接送机3:国际接送机4:马上用车5:国内包车
    @JSONField(name = "OrderID")
    private String orderId; //订单号
    @JSONField(name = "SelType")
    private String selType; //结算类型(M：月结，N：现付)
    @JSONField(name = "DelType")
    private String delType; //明细类型(O:出票，R：退票)
    @JSONField(name = "CtripCardNo")
    private String ctripCardNo; //携程卡号
    @JSONField(name = "UID")
    private String uid; //携程卡号
    @JSONField(name = "Amount")
    private BigDecimal amount; //卖价
    @JSONField(name = "CarAddTaxAmount")
    private BigDecimal carAddTaxAmount; //手续费（用车加收税额）
    @JSONField(name = "CarBasicFeeDetail")
    private String carBasicFeeDetail; //用车基础费用明细
    @JSONField(name = "CarValueAddFee")
    private BigDecimal carValueAddFee; //增值费用
    @JSONField(name = "CarValueAddFeeDetail")
    private String carValueAddFeeDetail; //用车增值费用明细
    @JSONField(name = "penaltyFee")
    private BigDecimal penaltyFee; //违约金
    /**
     * 实收实付=
     *      Amount(卖价)+CarValueAddFee(增值费用)+
     *      PenaltyFee(违约金)+ServerFee(商旅管理服务费)+
     *      ExpressFee(快递费)
     * */
    @JSONField(name = "RealAmount")
    private BigDecimal realAmount;
    @JSONField(name = "ServerFee")
    private BigDecimal serverFee; //商旅管理服务费
    @JSONField(name = "ExpressFee")
    private BigDecimal expressFee;//快递费
    @JSONField(name = "OrderType")
    private Integer orderType; //订单类型9：月结用车；10：现付用车
    @JSONField(name = "SubAccCheckBatchNo")
    private String SubAccCheckBatchNo; //结算子批次号
    @JSONField(name = "TripID")
    private Long tripId; //行程号，又名“行程打包单号”
    @JSONField(name = "SettlementCurrency")
    private String settlementCurrency; //结算币种
    @JSONField(name = "PostServiceFee")
    private BigDecimal postServiceFee; //后收商旅管理服务费 后收商旅管理服务费：指预订过程中不展示，仅在结算数据中体现。
    /**
     * 实收实付(含后收商旅管理服务费)=
     *      Amount(卖价)+CarValueAddFee(增值费用)+
     *      CarAddTaxAmount(用车加收税额)+PenaltyFee(违约金)+
     *      ServerFee(商旅管理服务费)+ExpressFee(快递费)+
     *      PostServiceFee（后收商旅管理服务费）
     * */
    @JSONField(name = "RealAmountHasPost")
    private BigDecimal realAmountHasPost;
    @JSONField(name = "IsChecked")
    private Boolean checked;
    @JSONField(name = "InvoiceIds")
    private List<String> invoiceIds;
    @JSONField(name = "BatchNoStartDate")
    private String batchNoStartDate; //批次起始日期,yyyyMMdd
    @JSONField(name = "BatchNoEndDate")
    private String batchNoEndDate;  //批次结束日期,yyyyMMdd
    @JSONField(name = "CorpID")
    private String corpID;  //公司ID
    @JSONField(name = "AccountID")
    private String accountID; //主账户ID
    @JSONField(name = "Dept1")
    private String dept1;
    @JSONField(name = "Dept2")
    private String dept2;
    @JSONField(name = "Dept3")
    private String dept3;
    @JSONField(name = "Dept4")
    private String dept4;
    @JSONField(name = "Dept5")
    private String dept5;
    @JSONField(name = "Dept6")
    private String dept6;
    @JSONField(name = "Dept7")
    private String dept7;
    @JSONField(name = "Dept8")
    private String dept8;
    @JSONField(name = "Dept9")
    private String dept9;
    @JSONField(name = "Dept10")
    private String dept10;
    @JSONField(name = "LoanServiceFee")
    private BigDecimal loanServiceFee; //垫资服务费
    @JSONField(name = "PayMixFlag")
    private String payMixFlag; //是否为混付 T=混付，F=非混付（打车和接送机输出此字段，租车和包车不输出）
    @JSONField(name = "PersonAmount")
    private BigDecimal personAmount; //个人支付金额（打车和接送机输出此字段，租车和包车不输出）

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    public void setDataChangeLastTime(Date dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSelType() {
        return selType;
    }

    public void setSelType(String selType) {
        this.selType = selType;
    }

    public String getDelType() {
        return delType;
    }

    public void setDelType(String delType) {
        this.delType = delType;
    }

    public String getCtripCardNo() {
        return ctripCardNo;
    }

    public void setCtripCardNo(String ctripCardNo) {
        this.ctripCardNo = ctripCardNo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCarAddTaxAmount() {
        return carAddTaxAmount;
    }

    public void setCarAddTaxAmount(BigDecimal carAddTaxAmount) {
        this.carAddTaxAmount = carAddTaxAmount;
    }

    public String getCarBasicFeeDetail() {
        return carBasicFeeDetail;
    }

    public void setCarBasicFeeDetail(String carBasicFeeDetail) {
        this.carBasicFeeDetail = carBasicFeeDetail;
    }

    public BigDecimal getCarValueAddFee() {
        return carValueAddFee;
    }

    public void setCarValueAddFee(BigDecimal carValueAddFee) {
        this.carValueAddFee = carValueAddFee;
    }

    public String getCarValueAddFeeDetail() {
        return carValueAddFeeDetail;
    }

    public void setCarValueAddFeeDetail(String carValueAddFeeDetail) {
        this.carValueAddFeeDetail = carValueAddFeeDetail;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getServerFee() {
        return serverFee;
    }

    public void setServerFee(BigDecimal serverFee) {
        this.serverFee = serverFee;
    }

    public BigDecimal getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(BigDecimal expressFee) {
        this.expressFee = expressFee;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getSubAccCheckBatchNo() {
        return SubAccCheckBatchNo;
    }

    public void setSubAccCheckBatchNo(String subAccCheckBatchNo) {
        SubAccCheckBatchNo = subAccCheckBatchNo;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public BigDecimal getPostServiceFee() {
        return postServiceFee;
    }

    public void setPostServiceFee(BigDecimal postServiceFee) {
        this.postServiceFee = postServiceFee;
    }

    public BigDecimal getRealAmountHasPost() {
        return realAmountHasPost;
    }

    public void setRealAmountHasPost(BigDecimal realAmountHasPost) {
        this.realAmountHasPost = realAmountHasPost;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<String> getInvoiceIds() {
        return invoiceIds;
    }

    public void setInvoiceIds(List<String> invoiceIds) {
        this.invoiceIds = invoiceIds;
    }

    public String getBatchNoStartDate() {
        return batchNoStartDate;
    }

    public void setBatchNoStartDate(String batchNoStartDate) {
        this.batchNoStartDate = batchNoStartDate;
    }

    public String getBatchNoEndDate() {
        return batchNoEndDate;
    }

    public void setBatchNoEndDate(String batchNoEndDate) {
        this.batchNoEndDate = batchNoEndDate;
    }

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getDept1() {
        return dept1;
    }

    public void setDept1(String dept1) {
        this.dept1 = dept1;
    }

    public String getDept2() {
        return dept2;
    }

    public void setDept2(String dept2) {
        this.dept2 = dept2;
    }

    public String getDept3() {
        return dept3;
    }

    public void setDept3(String dept3) {
        this.dept3 = dept3;
    }

    public String getDept4() {
        return dept4;
    }

    public void setDept4(String dept4) {
        this.dept4 = dept4;
    }

    public String getDept5() {
        return dept5;
    }

    public void setDept5(String dept5) {
        this.dept5 = dept5;
    }

    public String getDept6() {
        return dept6;
    }

    public void setDept6(String dept6) {
        this.dept6 = dept6;
    }

    public String getDept7() {
        return dept7;
    }

    public void setDept7(String dept7) {
        this.dept7 = dept7;
    }

    public String getDept8() {
        return dept8;
    }

    public void setDept8(String dept8) {
        this.dept8 = dept8;
    }

    public String getDept9() {
        return dept9;
    }

    public void setDept9(String dept9) {
        this.dept9 = dept9;
    }

    public String getDept10() {
        return dept10;
    }

    public void setDept10(String dept10) {
        this.dept10 = dept10;
    }

    public BigDecimal getLoanServiceFee() {
        return loanServiceFee;
    }

    public void setLoanServiceFee(BigDecimal loanServiceFee) {
        this.loanServiceFee = loanServiceFee;
    }

    public String getPayMixFlag() {
        return payMixFlag;
    }

    public void setPayMixFlag(String payMixFlag) {
        this.payMixFlag = payMixFlag;
    }

    public BigDecimal getPersonAmount() {
        return personAmount;
    }

    public void setPersonAmount(BigDecimal personAmount) {
        this.personAmount = personAmount;
    }
}
