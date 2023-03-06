package com.dne.ctrip.param;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CorpCustomInfoInfo extends AuthParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "AuthenticationInfoList")
    private List<CorpAuthEmployeeInfo> corpAuthEmployeeInfoList;

}
