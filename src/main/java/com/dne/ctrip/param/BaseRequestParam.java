package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class BaseRequestParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Auth")
    private AuthParam auth;

    public AuthParam getAuth() {
        return auth;
    }

    public void setAuth(AuthParam auth) {
        this.auth = auth;
    }
}

