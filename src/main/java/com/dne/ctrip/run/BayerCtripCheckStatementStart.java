package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

public class BayerCtripCheckStatementStart extends CtripAbsBaseStart{

    private static final Logger log = LoggerFactory.getLogger(BayerCtripCheckStatementStart.class);

    public static void main(String[] args) {
        log.info("Bayer ctrip check billFile start: " + Arrays.toString(args));
        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripCheckStatementBiz");
        if (null != riverBiz) {
            Map<String, Object> dataMap = Maps.newHashMap();
            if (args.length > 0) {// 如果命令行参数不为空
                dataMap.put("billFile", args[0]);
            }
            BayerCtripCheckStatementStart start = new BayerCtripCheckStatementStart();
            start.execute(riverBiz, dataMap, "Bayer ctrip employee.");
        }
        log.info("Bayer ctrip check billFile end.");
    }
}
