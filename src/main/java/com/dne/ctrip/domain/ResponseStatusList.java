package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class ResponseStatusList implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Status")
    private ResponseStatus responseStatus;

    @JSONField(name = "Result")
    private String status;

    @JSONField(name = "ErrorMessageList")
    private List<ErrorMessage> messages;



    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ErrorMessage> messages) {
        this.messages = messages;
    }
}
