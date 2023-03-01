package com.dne.ctrip.entity;

import java.io.Serializable;

public class SyncEmployeeInfo extends EmployeeInfo implements Serializable {

    private String batchNo;
    private String operateStatus;

    private String syncStatus;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
