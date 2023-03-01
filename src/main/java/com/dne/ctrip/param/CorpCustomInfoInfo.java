package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class CorpCustomInfoInfo extends AuthParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "AuthenticationInfoList")
    private List<CorpAuthEmployeeInfo> corpAuthEmployeeInfoList;


    public List<CorpAuthEmployeeInfo> getCorpAuthEmployeeInfoList() {
        return corpAuthEmployeeInfoList;
    }

    public void setCorpAuthEmployeeInfoList(List<CorpAuthEmployeeInfo> corpAuthEmployeeInfoList) {
        this.corpAuthEmployeeInfoList = corpAuthEmployeeInfoList;
    }
}
