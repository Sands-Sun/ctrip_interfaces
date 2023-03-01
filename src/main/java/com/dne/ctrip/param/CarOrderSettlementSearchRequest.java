package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class CarOrderSettlementSearchRequest extends BaseRequestParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "AccountId")
    private String accountId;
    @JSONField(name = "DateFrom",format = "yyyy-MM-dd")
    private Date dateFrom; //关联行程号、订单号和预订时间必填一项，格式：yyyy-MM-dd

    @JSONField(name = "DateTo",format = "yyyy-MM-dd")
    private Date dateTo; //关联行程号、订单号和预订时间必填一项，格式：yyyy-MM-dd

    @JSONField(name = "OrderID")
    private Long orderId;

    @JSONField(name = "BatchNo")
    private String batchNo;

    @JSONField(name = "SelType")
    private String selType; // 结算类型 月结:M；现付:N默认是所有

    @JSONField(name = "RecordId")
    private String recordId; //流水号  多个流水号以,号隔开，最多支持1000个

    @JSONField(name = "IsCompensation")
    private boolean isCompensation;  //默认为false，建议设置为true，在获取正常数据的同时，我们也会将最近更新的数据一起推送

    @JSONField(name = "PageIndex")
    private int pageIndex;

    @JSONField(name = "PageSize")
    private int pageSize;  //分页大小，默认10


    public CarOrderSettlementSearchRequest() {

    }

    public CarOrderSettlementSearchRequest(Date dateFrom, Date dateTo,
                                           int pageIndex, int pageSize) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSelType() {
        return selType;
    }

    public void setSelType(String selType) {
        this.selType = selType;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public boolean isCompensation() {
        return isCompensation;
    }

    public void setCompensation(boolean compensation) {
        isCompensation = compensation;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
