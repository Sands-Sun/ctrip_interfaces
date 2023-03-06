package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
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
}
