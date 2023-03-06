package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseStatusList implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Status")
    private ResponseStatus responseStatus;

    @JSONField(name = "Result")
    private String status;

    @JSONField(name = "ErrorMessageList")
    private List<ErrorMessage> messages;

}
