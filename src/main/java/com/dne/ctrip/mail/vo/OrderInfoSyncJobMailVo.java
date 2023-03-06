package com.dne.ctrip.mail.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderInfoSyncJobMailVo extends JobMailVo{

    private String startDate;
    private String endDate;
    private int syncOrderTotal;
    private int addOrderNum;
    private int updateOrderNum;
    public OrderInfoSyncJobMailVo(String mailType) {
        super(mailType);
    }
}
