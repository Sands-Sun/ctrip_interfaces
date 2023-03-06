package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarOrderAccountSettlementDetail implements Serializable {

    @JSONField(name = "AccountId")
    private String accountId;

    @JSONField(name = "CarSettlementDetailList")
    private List<CarOrderSettlementDetail> carOrderSettlementDetails;
}
