package com.dne.ctrip.mail.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobMailVo extends BaseMailVo{

    private String batchNo;
    private String prevBatchNo;
    private int errorCode;
    private String errorMessage;
    private String runStatus;
    public JobMailVo(String mailType) {
        super(mailType);
    }
}
