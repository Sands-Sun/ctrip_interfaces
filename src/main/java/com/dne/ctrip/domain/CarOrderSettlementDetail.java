package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarOrderSettlementDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "SettlementBaseInfo")
    private CarOrderSettlementBaseInfo carOrderSettlementBaseInfo;

    @JSONField(name = "OrderDetail")
    private CarOrderDetail carOrderDetail;
}
