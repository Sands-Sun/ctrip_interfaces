package com.dne.core.basic.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class JobStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String jobName;

    private String runStatus;

    private int errorCode;

    private String errorMessage;

    private String batchId;

    private String currentBatchId;

    private Date beginTime;

    private Date endTime;

    private Date createdDate;

    private Date lastModifiedDate;

}
