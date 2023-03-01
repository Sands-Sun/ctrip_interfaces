package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BayerCtripEmployeeInfoSyncStart extends CtripAbsBaseStart {

    private static final Logger log = LoggerFactory.getLogger(BayerCtripEmployeeInfoSyncStart.class);

    public static void main(String[] args) {
        log.info("Bayer ctrip sync employee info start ...");
        String version = args.length > 0 ? args[0] : "V1";
        log.info("Current run Version: {}", version);
        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripEmployeeInfoSyncBiz" + version);
        if (null != riverBiz) {
            Map<String, Object> dataMap = Maps.newHashMap();
            BayerCtripEmployeeInfoSyncStart start = new BayerCtripEmployeeInfoSyncStart();
            start.execute(riverBiz, dataMap, "Bayer ctrip employee.");
        }
        log.info("Bayer ctrip sync employee info end.");
    }
}
