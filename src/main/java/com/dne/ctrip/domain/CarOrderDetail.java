package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class CarOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "OrderBaseInfo")
    private CarOrderBaseInfo carOrderBaseInfo;
    @JSONField(name = "CorpInfo")
    private CarOrderCorpInfo carOrderCorpInfo;
    @JSONField(name = "QuickProductInfo")
    private CarOrderQuickProductInfo quickProductInfo;
    @JSONField(name = "PassengerInfoList")
    private List<CarOrderPassengerInfo> carOrderPassengerInfoList;

    public CarOrderBaseInfo getCarOrderBaseInfo() {
        return carOrderBaseInfo;
    }

    public void setCarOrderBaseInfo(CarOrderBaseInfo carOrderBaseInfo) {
        this.carOrderBaseInfo = carOrderBaseInfo;
    }

    public CarOrderCorpInfo getCarOrderCorpInfo() {
        return carOrderCorpInfo;
    }

    public void setCarOrderCorpInfo(CarOrderCorpInfo carOrderCorpInfo) {
        this.carOrderCorpInfo = carOrderCorpInfo;
    }

    public CarOrderQuickProductInfo getQuickProductInfo() {
        return quickProductInfo;
    }

    public void setQuickProductInfo(CarOrderQuickProductInfo quickProductInfo) {
        this.quickProductInfo = quickProductInfo;
    }

    public List<CarOrderPassengerInfo> getCarOrderPassengerInfoList() {
        return carOrderPassengerInfoList;
    }

    public void setCarOrderPassengerInfoList(List<CarOrderPassengerInfo> carOrderPassengerInfoList) {
        this.carOrderPassengerInfoList = carOrderPassengerInfoList;
    }
}
