package com.dne.ctrip.run;

import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.DateUtils;
import com.dne.ctrip.mail.vo.OrderInfoSyncJobMailVo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static com.dne.core.common.Constant.CTRIP_ORDER_INFO_SYNC_JOB_MAIL_TYPE;

public class BayerCtripOrderInfoSyncStart extends CtripAbsBaseStart{


    private static final Logger log = LoggerFactory.getLogger(BayerCtripOrderInfoSyncStart.class);

    public BayerCtripOrderInfoSyncStart() {
        super(new OrderInfoSyncJobMailVo(CTRIP_ORDER_INFO_SYNC_JOB_MAIL_TYPE));
    }

    public static void main(String[] args) {
        log.info("Bayer ctrip order sync start: " + Arrays.toString(args));

        CtripAbsRiverBiz riverBiz = (CtripAbsRiverBiz)applicationContext.getBean("ctripOrderInfoSyncBiz");
        if (null != riverBiz) {
            Map<String, Object> dataMap = Maps.newHashMap();
            if (args.length <= 0) {
                // 如果命令行参数为空，默认为前天日期 日期格式 yyyy-MM-dd
                String startDate = DateUtils.getCustomDate(new Date(), -7);
                String endDate = DateUtils.getCustomDate(new Date(), -1);
                args = new String[] { startDate, endDate };
            }
            dataMap.put("start_date", args[0]);
            dataMap.put("end_date", args[1]);
            BayerCtripOrderInfoSyncStart start = new BayerCtripOrderInfoSyncStart();
            //[Bayer Notice] Bayer Ctrip Check Bill Statement Job
            start.execute(riverBiz, dataMap);
        }
        log.info("Bayer didi order sync end.");
    }
}
