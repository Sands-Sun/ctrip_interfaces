package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;
import com.dne.ctrip.entity.EmployeeInfo;

import java.io.Serializable;
import java.util.List;

public class CorpAuthEmployee extends EmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "SubAccountName")
    private String subAccountName;

    @JSONField(name = "ConcurAccount")
    private String concurAccount;

    @JSONField(name = "Valid")
    private String valid; // A-在职, I-离职

    @JSONField(name = "CorpCardType")
    private String corpCardType = "B"; //代订类型：C（个人卡），B（代订卡）：代订范围默认整个公司，D（虚卡）

    @JSONField(name = "ResRange")
    private String resRange = "F"; //C：公司、F：主账户及其他、S：子账户及其他、O：其他（根据代订关系配置）；（当代订类型为B（代订卡）时，代订范围不能为空）

    @JSONField(name = "CostCenterList")
    private List<CorpCostCenter> corpCostCenters;

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getSubAccountName() {
        return subAccountName;
    }

    public void setSubAccountName(String subAccountName) {
        this.subAccountName = subAccountName;
    }

    public String getConcurAccount() {
        return concurAccount;
    }

    public void setConcurAccount(String concurAccount) {
        this.concurAccount = concurAccount;
    }

    public String getCorpCardType() {
        return corpCardType;
    }

    public void setCorpCardType(String corpCardType) {
        this.corpCardType = corpCardType;
    }

    public String getResRange() {
        return resRange;
    }

    public void setResRange(String resRange) {
        this.resRange = resRange;
    }

    public List<CorpCostCenter> getCorpCostCenters() {
        return corpCostCenters;
    }

    public void setCorpCostCenters(List<CorpCostCenter> corpCostCenters) {
        this.corpCostCenters = corpCostCenters;
    }
}
