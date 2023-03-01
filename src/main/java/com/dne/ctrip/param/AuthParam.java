package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class AuthParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Language")
    private String language = "zh-CN";

    @JSONField(name = "Ticket")
    private String ticket;

    @JSONField(name = "AppKey")
    private String appKey;

    @JSONField(name = "CorporationID")
    private String corporationId;

    @JSONField(name = "appSecurity")
    private String appSecurity;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public String getAppSecurity() {
        return appSecurity;
    }

    public void setAppSecurity(String appSecurity) {
        this.appSecurity = appSecurity;
    }
}
