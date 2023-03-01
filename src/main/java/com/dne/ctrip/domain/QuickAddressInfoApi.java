package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class QuickAddressInfoApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "CityId")
    private String cityId;          //城市id
    @JSONField(name = "CityName")
    private String cityName;        //城市名称
    @JSONField(name = "Address")
    private String address;         //地址标题
    @JSONField(name = "AddressDetail")
    private String addressDetail;   //详细地址

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
