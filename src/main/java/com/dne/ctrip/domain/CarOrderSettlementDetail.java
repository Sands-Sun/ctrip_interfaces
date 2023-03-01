package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class CarOrderSettlementDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "SettlementBaseInfo")
    private CarOrderSettlementBaseInfo carOrderSettlementBaseInfo;
    @JSONField(name = "OrderDetail")
    private CarOrderDetail carOrderDetail;

    public CarOrderSettlementBaseInfo getCarOrderSettlementBaseInfo() {
        return carOrderSettlementBaseInfo;
    }

    public void setCarOrderSettlementBaseInfo(CarOrderSettlementBaseInfo carOrderSettlementBaseInfo) {
        this.carOrderSettlementBaseInfo = carOrderSettlementBaseInfo;
    }

    public CarOrderDetail getCarOrderDetail() {
        return carOrderDetail;
    }

    public void setCarOrderDetail(CarOrderDetail carOrderDetail) {
        this.carOrderDetail = carOrderDetail;
    }
}
