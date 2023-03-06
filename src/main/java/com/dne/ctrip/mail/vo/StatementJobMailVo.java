package com.dne.ctrip.mail.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatementJobMailVo extends JobMailVo{

    private String statementDate;
    private String statementFile;
    private int orderNumTotal;

    private int orderRefundNumTotal;
    private int noExistOrderNum;
    private int repeatOrderNum;
    private int largeAmtOrderNum;
    public StatementJobMailVo(String mailType) {
        super(mailType);
    }

   }
