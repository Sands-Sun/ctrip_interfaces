package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ResponseStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "Success")
    private Boolean success;

    @JSONField(name = "ErrorCode")
    private Integer errorCode;

    @JSONField(name = "Message")
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
