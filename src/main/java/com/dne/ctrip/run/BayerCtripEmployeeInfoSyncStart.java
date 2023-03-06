package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.ctrip.mail.vo.EmployeeSyncJobMailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static com.dne.core.common.Constant.CTRIP_EMPLOYEE_SYNC_JOB_JOB_MAIL_TYPE;

public class BayerCtripEmployeeInfoSyncStart extends CtripAbsBaseStart {

    private static final Logger log = LoggerFactory.getLogger(BayerCtripEmployeeInfoSyncStart.class);

    public BayerCtripEmployeeInfoSyncStart() {
        super(new EmployeeSyncJobMailVo(CTRIP_EMPLOYEE_SYNC_JOB_JOB_MAIL_TYPE));
    }
    public static void main(String[] args) {
        log.info("Bayer ctrip sync employee info start ...");
        String version = args.length > 0 ? args[0] : "V2";
        log.info("Current run Version: {}", version);
        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripEmployeeInfoSyncBiz" + version);
        if (null != riverBiz) {
            BayerCtripEmployeeInfoSyncStart start = new BayerCtripEmployeeInfoSyncStart();
            start.execute(riverBiz, Collections.emptyMap());
        }
        log.info("Bayer ctrip sync employee info end.");
    }
}
