package com.dne.core.basic.dao;

import com.dne.core.basic.entity.JobStatus;
import com.dne.core.basic.entity.JobStatusDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JobStatusDao {

    JobStatus getJobStatus(String jobName);

    void saveJobStatus(JobStatus jobStatus);

    void updateJobStatus(JobStatus jobStatus);

    void batchSaveStatusDetail(@Param("details") List<JobStatusDetail> details);

    List<JobStatusDetail> getJobStatusDetail(String jobName, String batchId);
}
