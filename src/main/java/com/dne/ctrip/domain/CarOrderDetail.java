package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
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

}
