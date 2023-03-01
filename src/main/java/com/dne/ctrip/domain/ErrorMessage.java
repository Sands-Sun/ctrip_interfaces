package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Sequence")
    private Integer sequence;

    @JSONField(name = "ErrorCode")
    private String errorCode;

    @JSONField(name = "Message")
    private String errorMessage;

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
