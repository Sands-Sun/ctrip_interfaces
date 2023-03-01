package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class CorpAuthEmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "Sequence")
    private String sequence;
    @JSONField(name = "Authentication")
    private CorpAuthEmployee corpAuthEmployee;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public CorpAuthEmployee getCorpAuthEmployee() {
        return corpAuthEmployee;
    }

    public void setCorpAuthEmployee(CorpAuthEmployee corpAuthEmployee) {
        this.corpAuthEmployee = corpAuthEmployee;
    }
}
