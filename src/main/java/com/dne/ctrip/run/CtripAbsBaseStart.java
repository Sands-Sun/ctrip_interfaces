package com.dne.ctrip.run;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.dne.core.common.*;
import com.dne.core.util.StringUtils;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;


public class CtripAbsBaseStart extends AbsBaseStart {

    private static final Logger log = LoggerFactory.getLogger(CtripAbsBaseStart.class);


    public CtripAbsBaseStart() {
        super(Constant.CTRIP_LOG_FILE);
    }

    @Override
    public void handleJobSuccess(BaseAbsRiverBiz riverBiz, Date jobBeginTime, Date jobEndTime) {
        CtripAbsRiverBiz ctripAbsRiverBiz = (CtripAbsRiverBiz) riverBiz;
        log.debug("ctrip job: {} success jobBeginTime: {}, jobEndTime: {}",
                ctripAbsRiverBiz.getJobName(),jobBeginTime,jobEndTime);
        ctripAbsRiverBiz.saveOrUpdateJobStatusSuccess(jobBeginTime,jobEndTime);
    }

    @Override
    public void handleJobFail(BaseAbsRiverBiz riverBiz,Date jobBeginTime, Date jobFailEndTime,Exception e) {
        CtripAbsRiverBiz ctripAbsRiverBiz = (CtripAbsRiverBiz) riverBiz;
        log.debug("ctrip job: {} fail jobBeginTime: {}, jobEndTime: {}",
                ctripAbsRiverBiz.getJobName(),jobBeginTime,jobFailEndTime);
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
        ctripAbsRiverBiz.saveOrUpdateJobStatusError(
                jobBeginTime,jobFailEndTime, errorCode, errorMessage);

        if(StringUtils.isNotEmpty(detailErrorMessage)){
            ctripAbsRiverBiz.saveJobStatusDetailError(detailErrorMessage);
        }
    }
}
