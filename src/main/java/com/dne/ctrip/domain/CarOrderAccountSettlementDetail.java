package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class CarOrderAccountSettlementDetail implements Serializable {

    @JSONField(name = "AccountId")
    private String accountId;

    @JSONField(name = "CarSettlementDetailList")
    private List<CarOrderSettlementDetail> carOrderSettlementDetails;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<CarOrderSettlementDetail> getCarOrderSettlementDetails() {
        return carOrderSettlementDetails;
    }

    public void setCarOrderSettlementDetails(List<CarOrderSettlementDetail> carOrderSettlementDetails) {
        this.carOrderSettlementDetails = carOrderSettlementDetails;
    }
}
