package com.dne.ctrip.mail.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeSyncJobMailVo extends JobMailVo {

    private int totalSync;
    private int beforeSyncTotalHRCentral;
    private int afterSyncTotalHRCentral;
    private int totalSyncNewCreate;
    private int totalSyncModified;
    private int totalSyncDeleted;

    public EmployeeSyncJobMailVo(String mailType) {
        super(mailType);
    }

}
