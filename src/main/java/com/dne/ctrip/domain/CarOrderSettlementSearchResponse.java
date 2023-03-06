package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarOrderSettlementSearchResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "TotalRecord")
    private Integer totalRecord;

    @JSONField(name = "TotalSize")
    private Integer totalSize;

    @JSONField(name = "CarOrderAccountSettlementList")
    private List<CarOrderAccountSettlementDetail> carOrderAccountSettlementDetailList; //主账户用车订单结算信息数据集合

    @JSONField(name = "Status")
    private ResponseStatus status;

}
