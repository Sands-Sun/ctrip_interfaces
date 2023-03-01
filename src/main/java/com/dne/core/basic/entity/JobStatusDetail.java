package com.dne.core.basic.entity;

import java.io.Serializable;
import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
