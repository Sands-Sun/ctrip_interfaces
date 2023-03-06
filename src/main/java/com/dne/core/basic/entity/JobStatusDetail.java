package com.dne.core.basic.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class JobStatusDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String jobName;

    private String batchId;

    private String refId;

    private String detailMessage;

    private Date createdDate;

    private Date lastModifiedDate;

    public JobStatusDetail() {

    }

    public JobStatusDetail(String jobName, String batchId, String refId, String detailMessage) {
        this.jobName = jobName;
        this.batchId = batchId;
        this.refId = refId;
        this.detailMessage = detailMessage;
    }

}
