package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class DestinationChangeInfoApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "CurrentAddress")
    private QuickAddressInfoApi currentAddress;
    @JSONField(name = "BeforeAddress")
    private QuickAddressInfoApi beforeAddress;
    @JSONField(name = "AfterAddress")
    private QuickAddressInfoApi afterAddress;

    public QuickAddressInfoApi getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(QuickAddressInfoApi currentAddress) {
        this.currentAddress = currentAddress;
    }

    public QuickAddressInfoApi getBeforeAddress() {
        return beforeAddress;
    }

    public void setBeforeAddress(QuickAddressInfoApi beforeAddress) {
        this.beforeAddress = beforeAddress;
    }

    public QuickAddressInfoApi getAfterAddress() {
        return afterAddress;
    }

    public void setAfterAddress(QuickAddressInfoApi afterAddress) {
        this.afterAddress = afterAddress;
    }
}
