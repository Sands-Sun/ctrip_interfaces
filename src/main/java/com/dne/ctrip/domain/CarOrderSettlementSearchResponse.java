package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class CarOrderSettlementSearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "TotalRecord")
    private Integer totalRecord;
    @JSONField(name = "TotalSize")
    private Integer totalSize;
    @JSONField(name = "CarOrderAccountSettlementList")
    private List<CarOrderAccountSettlementDetail> carOrderAccountSettlementDetailList; //主账户用车订单结算信息数据集合
    @JSONField(name = "Status")
    private ResponseStatus status;

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<CarOrderAccountSettlementDetail> getCarOrderAccountSettlementDetailList() {
        return carOrderAccountSettlementDetailList;
    }

    public void setCarOrderAccountSettlementDetailList(List<CarOrderAccountSettlementDetail> carOrderAccountSettlementDetailList) {
        this.carOrderAccountSettlementDetailList = carOrderAccountSettlementDetailList;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
