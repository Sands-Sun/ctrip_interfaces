package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Sequence")
    private Integer sequence;

    @JSONField(name = "ErrorCode")
    private String errorCode;

    @JSONField(name = "Message")
    private String errorMessage;

}
