package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class DestinationChangeInfoApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "CurrentAddress")
    private QuickAddressInfoApi currentAddress;

    @JSONField(name = "BeforeAddress")
    private QuickAddressInfoApi beforeAddress;

    @JSONField(name = "AfterAddress")
    private QuickAddressInfoApi afterAddress;

}
