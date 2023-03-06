package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "Success")
    private Boolean success;

    @JSONField(name = "ErrorCode")
    private Integer errorCode;

    @JSONField(name = "Message")
    private String message;

}
