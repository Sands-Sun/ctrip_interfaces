package com.dne.ctrip.mail.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderReportMailVo extends JobMailVo{

    private String startDate;
    private String endDate;
    private String reportType;
    private int orderTotal;

    public OrderReportMailVo(String mailType) {
        super(mailType);
    }
}
