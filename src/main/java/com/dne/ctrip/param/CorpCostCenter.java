package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class CorpCostCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "CostCenterType")
     private String type = "C1";

    @JSONField(name = "CostCenterContent")
     private String content;

    @JSONField(name = "CostCenterContentEN")
     private String contentEN;

    @JSONField(name = "IsDefault")
     private Boolean isDefault = true;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentEN() {
        return contentEN;
    }

    public void setContentEN(String contentEN) {
        this.contentEN = contentEN;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
