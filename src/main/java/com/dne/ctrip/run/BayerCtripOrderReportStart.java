package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.DateUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class BayerCtripOrderReportStart extends CtripAbsBaseStart{

    private static final Logger log = LoggerFactory.getLogger(BayerCtripOrderReportStart.class);

    public static void main(String[] args) {
        log.info("Bayer ctrip order report generate start: " + Arrays.toString(args));

        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripOrderReportGenerateBiz");
        if (null != riverBiz) {
            Map<String, Object> dataMap = Maps.newHashMap();
            if (args.length <= 0) {// 如果命令行参数为空，默认为上周一到周日
                Date lastWeekMonday = DateUtils.geLastWeekMonday(new Date());
                args = new String[] { DateUtils.dateToStr(lastWeekMonday, "yyyy-MM-dd"),
                        DateUtils.getCustomDate(lastWeekMonday, 7) };
            }
            dataMap.put("startDate", args[0] );
            dataMap.put("endDate", args[1] + " 23:59:59.999");

            BayerCtripOrderReportStart start = new BayerCtripOrderReportStart();
            start.execute(riverBiz, dataMap, "Bayer order report generate");
        }

        log.info("Bayer ctrip order report generate end.");
    }
}
