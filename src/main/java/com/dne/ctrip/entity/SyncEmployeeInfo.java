package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SyncEmployeeInfo extends EmployeeInfo implements Serializable {

    private String batchNo;

    private String operateStatus;

    private String syncStatus;

}
