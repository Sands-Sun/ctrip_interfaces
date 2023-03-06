package com.dne.core.common;

import com.dne.core.basic.dao.JobStatusDao;
import com.dne.core.basic.entity.JobStatus;
import com.dne.core.basic.entity.JobStatusDetail;
import com.dne.core.util.DateUtils;
import com.dne.ctrip.mail.vo.BaseMailVo;
import com.dne.ctrip.mail.vo.JobMailVo;
import com.dne.ctrip.river.CtripRiverServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import static com.dne.core.common.Constant.CTRIP_JOB_FAIL_STATUS;
import static com.dne.core.common.Constant.CTRIP_JOB_SUCCESS_STATUS;

public abstract class CtripAbsRiverBiz<T extends JobMailVo>{

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected JobStatus jobStatus;

    @Autowired
    protected CtripRiverServiceHandler handler;

    protected static final int ctripPageSize = 100;

    public CtripAbsRiverBiz(String jobName) {
        log.info("JobName: {}", jobName);
        this.jobName = jobName;
        batchNo = this.getBatchNo();
    }
    protected final String jobName;
    protected final String batchNo;

    @Value("${syn.batch.count}")
    protected Integer batchCount;

    @Value("${syn.batch.query.count}")
    protected Integer batchQueryCount;

    @Autowired
    protected JobStatusDao jobStatusDao;

    @PostConstruct
    public void init() {
        log.info("Init CtripAbsRiver...");
        jobStatus = jobStatusDao.getJobStatus(jobName);
        hookInit();
    }

    public String getJobName() {
        return jobName;
    }

    protected abstract void hookInit();

    public void fillPervJobStatus(T t) {
        log.info("Fill in Previous job status to jobMailVo...");
        t.setBatchNo(batchNo);
        t.setPrevBatchNo(jobStatus.getBatchId());
        t.setRunStatus(jobStatus.getRunStatus());
        t.setErrorMessage(jobStatus.getErrorMessage());
        t.setErrorCode(jobStatus.getErrorCode());
    }

    protected String getBatchNo() {
        return DateUtils.getCustomDate(0).replace("-", "") + (int)((Math.random() * 9 + 1) * 100000);
    }

    public abstract void processData(Map<String, Object> dataMap, T t);


    public void saveOrUpdateJobStatusSuccess(Date beginTime, Date endTime) {
        saveOrUpdateJobStatus(beginTime, endTime,CTRIP_JOB_SUCCESS_STATUS,0, "NA");
    }

    public void saveOrUpdateJobStatusError(Date beginTime, Date endTime, int errorCode,
                                           String errorMessage) {
        saveOrUpdateJobStatus(beginTime, endTime,CTRIP_JOB_FAIL_STATUS,errorCode, errorMessage);
    }

    public void saveJobStatusDetailError(String detailErrorMessage) {
        JobStatusDetail detail = new JobStatusDetail(jobName,batchNo, "NA", detailErrorMessage);
        jobStatusDao.batchSaveStatusDetail(Collections.singletonList(detail));
    }


    public void saveOrUpdateJobStatus(Date beginTime, Date endTime,String runStatus, int errorCode,
                                      String errorMessage) {
        boolean saveFlag = Objects.isNull(jobStatus);
        fillJobStatus(beginTime,endTime,runStatus,
                saveFlag,errorCode,errorMessage);
        saveOrUpdateStatus(saveFlag);
    }

    private void fillJobStatus(Date beginTime,Date endTime,String runStatus, boolean saveFlag) {
        Date currentDate = new Date();
        jobStatus = saveFlag ? new JobStatus() :jobStatus;
        jobStatus.setJobName(jobName);
        jobStatus.setRunStatus(runStatus);
        jobStatus.setBatchId(batchNo);
        jobStatus.setBeginTime(beginTime);
        jobStatus.setEndTime(endTime);
        jobStatus.setLastModifiedDate(currentDate);
        jobStatus.setCreatedDate(currentDate);
    }

    private void fillJobStatus(Date beginTime,Date endTime,String runStatus, boolean saveFlag,
                               int errorCode, String errorMessage) {
        fillJobStatus(beginTime,endTime,runStatus,saveFlag);
        jobStatus.setErrorCode(errorCode);
        jobStatus.setErrorMessage(errorMessage);
    }

    private void saveOrUpdateStatus(boolean saveFlag) {
        if(saveFlag){
            log.info("save job status...");
            jobStatusDao.saveJobStatus(jobStatus);
        }else {
            log.info("update job status...");
            jobStatusDao.updateJobStatus(jobStatus);
        }
    }
}
