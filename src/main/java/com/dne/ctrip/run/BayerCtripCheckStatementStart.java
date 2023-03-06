package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.ctrip.mail.vo.BaseMailVo;
import com.dne.ctrip.mail.vo.EmployeeSyncJobMailVo;
import com.dne.ctrip.mail.vo.StatementJobMailVo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

import static com.dne.core.common.Constant.CTRIP_CHECK_STATEMENT_JOB_MAIL_TYPE;

public class BayerCtripCheckStatementStart extends CtripAbsBaseStart{

    private static final Logger log = LoggerFactory.getLogger(BayerCtripCheckStatementStart.class);

    public BayerCtripCheckStatementStart() {
        super(new StatementJobMailVo(CTRIP_CHECK_STATEMENT_JOB_MAIL_TYPE));
    }


    public static void main(String[] args) {
        log.info("Bayer ctrip check billFile start: " + Arrays.toString(args));
        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripCheckStatementBiz");
        if (null != riverBiz) {
            Map<String, Object> dataMap = Maps.newHashMap();
            if (args.length > 0) {// 如果命令行参数不为空
                dataMap.put("billFile", args[0]);
            }
            BayerCtripCheckStatementStart start = new BayerCtripCheckStatementStart();
            start.execute(riverBiz, dataMap);
        }
        log.info("Bayer ctrip check billFile end.");
    }
}
