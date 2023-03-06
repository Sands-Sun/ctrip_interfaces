package com.dne.ctrip.run;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.dne.core.common.*;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.mail.vo.BaseMailVo;
import com.dne.ctrip.mail.vo.JobMailVo;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

import static com.dne.core.common.Constant.CTRIP_LOG_FILE;


public class CtripAbsBaseStart extends AbsBaseStart {

    private static final Logger log = LoggerFactory.getLogger(CtripAbsBaseStart.class);


    public CtripAbsBaseStart(JobMailVo mailVo) {
        super(CTRIP_LOG_FILE, mailVo);
    }

    @Override
    public void handleJobSuccess(CtripAbsRiverBiz riverBiz, Date jobBeginTime, Date jobEndTime) {
        log.debug("ctrip job: {} success jobBeginTime: {}, jobEndTime: {}",
                riverBiz.getJobName(),jobBeginTime,jobEndTime);
        riverBiz.saveOrUpdateJobStatusSuccess(jobBeginTime,jobEndTime);
    }

    @Override
    public void handleJobFail(CtripAbsRiverBiz riverBiz,Date jobBeginTime, Date jobFailEndTime,Exception e) {
        log.debug("ctrip job: {} fail jobBeginTime: {}, jobEndTime: {}",
                riverBiz.getJobName(),jobBeginTime,jobFailEndTime);
        int errorCode = 9999;
        String errorMessage;
        String detailErrorMessage = null;
        if(e instanceof SQLServerException){
           SQLServerException sqlServerException = (SQLServerException) e;
           errorCode = sqlServerException.getErrorCode();
           errorMessage = sqlServerException.getMessage();
        }else if (e instanceof ExcelAnalysisException) {
            ExcelAnalysisException analysisException = (ExcelAnalysisException) e;
            errorMessage =  analysisException.getMessage();
            errorCode = 3100;
        }else if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            errorCode = baseException.getErrorCode();
            errorMessage = baseException.getErrorMessage();
            if(Objects.nonNull(e.getCause()) && Objects.nonNull(e.getCause().getMessage())){
                detailErrorMessage = e.getCause().getMessage();
            }
        }else {
          errorMessage = e.getMessage() == null ? e.toString() : e.getMessage();
        }
        riverBiz.saveOrUpdateJobStatusError(
                jobBeginTime,jobFailEndTime, errorCode, errorMessage);

        if(StringUtils.isNotEmpty(detailErrorMessage)){
            riverBiz.saveJobStatusDetailError(detailErrorMessage);
        }
    }
}
