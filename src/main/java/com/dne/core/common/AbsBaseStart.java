package com.dne.core.common;

import cn.hutool.core.io.resource.ClassPathResource;
import com.dne.core.util.Global;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.mail.template.CommonSendMailUtils;
import com.dne.ctrip.mail.vo.BaseMailVo;
import com.dne.ctrip.mail.vo.JobMailVo;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.NamedThreadLocal;

import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public abstract class AbsBaseStart {

    private static final Logger log = LoggerFactory.getLogger(AbsBaseStart.class);

    protected static final ThreadLocal<Integer> retryCounterThreadLocal = new NamedThreadLocal<Integer>("River-RetryCounter");

    protected static ApplicationContext applicationContext = null;

    protected static int retryCount = 0;

    protected JobMailVo mailVo;

    private static String receiveSynLogEmail;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-context.xml");
        retryCount = Integer.parseInt(Global.getConfig("syn.retry.count"));
        receiveSynLogEmail = Global.getConfig("syn.log.email");
    }


    public AbsBaseStart(String logFile, JobMailVo mailVo) {
        URL log4jFile = new ClassPathResource("config/" + logFile).getUrl();
        DOMConfigurator.configure(log4jFile);
        this.mailVo = mailVo;
        mailVo.setMailTo(receiveSynLogEmail);
    }


    /**
     * 业务处理
     * @param riverBiz
     * @param dataMap
     */
    protected void execute(CtripAbsRiverBiz riverBiz,Map<String, Object> dataMap) {
        String errorLog = null;
        Date jobBeginTime = new Date();
        log.debug("job begin time: {}", jobBeginTime);
        try {
            retryCounterThreadLocal.set(0);
            riverBiz.fillPervJobStatus(mailVo);
            riverBiz.processData(dataMap,mailVo);
            Date jobEndTime = new Date();
            log.debug("end job time: {}", jobEndTime);
            handleJobSuccess(riverBiz,jobBeginTime,jobEndTime);
        } catch (Exception e) {
            Date jobFailEndTime = new Date();
            log.debug("job fail end time: {}", jobFailEndTime);
            handleJobFail(riverBiz,jobBeginTime,jobFailEndTime,e);
            e.printStackTrace();
            errorLog = this.getStackTrace(e);
            if (retryCount > 0) {
                this.retry(riverBiz, dataMap);
            }
        } finally {
            retryCounterThreadLocal.remove();
        }
        if(errorLog != null){
            mailVo.setErrorLog(errorLog);
        }
        CommonSendMailUtils.sendMail(mailVo);
    }

    public abstract void handleJobSuccess(CtripAbsRiverBiz riverBiz,Date jobBeginTime, Date jobEndTime);

    public abstract void handleJobFail(CtripAbsRiverBiz riverBiz,Date jobBeginTime, Date jobFailEndTime, Exception e);

    /**
     * 出错重试
     * @param riverSynBiz
     * @param dataMap
     */
    private void retry(CtripAbsRiverBiz riverSynBiz, Map<String, Object> dataMap) {
        int retryCounter = retryCounterThreadLocal.get();
        retryCounter++;
        log.info("Bayer process retry: class={}, retryCounter={}", riverSynBiz.getClass().getName(), retryCounter);
        try {
            retryCounterThreadLocal.set(retryCounter);
            riverSynBiz.processData(dataMap,mailVo);
        } catch (Exception e) {
            e.printStackTrace();
            if (retryCounter < retryCount) {
                retry(riverSynBiz, dataMap);
            }
        }
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
