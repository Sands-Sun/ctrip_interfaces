package com.dne.ctrip.syndata.biz;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.dne.core.common.Constant;
import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.DateUtils;
import com.dne.core.util.ExcelExportUtils;
import com.dne.core.util.Global;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.entity.OrderReportInfo;
import com.dne.ctrip.mail.template.CommonSendMailUtils;
import com.dne.ctrip.syndata.service.OrderInfoService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class CtripOrderReportGenerateBiz extends CtripAbsRiverBiz {


    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${order.report.path}")
    private String orderReportPath;

    @Value("${order.report.remindUser}")
    private String remindUser;

    @Value("${order.report.remindSubject}")
    private String remindSubject;

    public CtripOrderReportGenerateBiz() {
        super(Constant.CTRIP_ORDER_REPORT);
    }
    @Override
    protected void hookInit() {

    }


    @Override
    public String processLog(Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public void processData(Map<String, Object> dataMap) {
        log.info("Generate didi order report start: " + dataMap);
        String startDate= Convert.toStr(dataMap.get("startDate"));
        String endDate=Convert.toStr(dataMap.get("endDate"));
        String mailType= "month".equals(dataMap.get("type"))?"ctripMonthReport":"ctripOrderReport";
        //如果是Month 类型则需要将上个月的订单数据在获取一遍。
        if("ctripMonthReport".equals(mailType)) {
            getLastMonthAllOrderInfo(dataMap);
        }
        dataMap.put("startDate", startDate);
        dataMap.put("endDate", endDate);
        List<OrderReportInfo> orderReportList = orderInfoService.getOrderReportList(dataMap);
        int orderTotal = orderReportList.size();
        int mailTotal = 0 ;
        log.info("Generate didi order report num: " + orderTotal);

        if (orderTotal > 0) {
            // 把订单按照cwid分组
            Multimap<String, OrderReportInfo> orderGroupMap = ArrayListMultimap.create();
            for (OrderReportInfo order : orderReportList) {
                String ccCwid = null;
                if(StrUtil.isBlank(order.getCcCwid())) {
                    ccCwid = "Blank";
                }else {
                    ccCwid = order.getCcCwid();
                }
                orderGroupMap.put(ccCwid, order);
            }

            String[] headNames = { "CWID", "Name", "Cost Center", "Start Date", "End Date", "Actual Price(RMB)", "Order Id", "City Name", "Start Address", "End Address", "Remark", "Distance(km)","Category","Regulation","Pay Type"};
            String[] fieldNames = { "cwid", "employeeName", "ccCode", "startTime", "endTime", "actualPrice", "orderId", "cityName","startName", "endName", "remark","distance","category","regulation","payTypeDesc"};
            Map<String, Collection<OrderReportInfo>> orderListMap = orderGroupMap.asMap();
            try {
                for (Map.Entry<String, Collection<OrderReportInfo>> orderGroup : orderListMap.entrySet()) {
                    log.info("Generate ctrip order report cost center: " + orderGroup.getKey());
                    Collection<OrderReportInfo> orderList = orderGroup.getValue();
                    String reportExcelFile = this.getOrderReportFile(dataMap, orderGroup.getKey());
                    log.info("Generate ctrip order report excel file: " + reportExcelFile);
                    String reportTitle = "Bayer Ctrip Order Report " + dataMap.get("startDate").toString().replace("-", "/") + " - " + dataMap.get("endDate").toString().substring(0, 10).replace("-", "/");
                    ExcelExportUtils.export2ExcelByPojo(orderList, headNames, fieldNames, orderGroup.getKey(), reportTitle, reportExcelFile);

                    // 把生产的Excel报表发送给打车用户对应的Approve cwid
                    OrderReportInfo reprot = orderList.iterator().next();
                    if (null != reprot && StringUtils.isNotEmpty(reprot.getCcCwid())) {
                        log.info("Generate ctrip order report send email: " + reprot.getCcCwid());
                        mailTotal++;
                        if("BLANK".equals(reprot.getCcCwid())){
                            String email = Global.getConfig("order.report.error.mail");
                            CommonSendMailUtils.sendMail(CommonSendMailUtils.MAIL_PROCESS_CTRIP_TYPE,
                                    reprot, email, mailType, reportExcelFile);
                        }else{
                            CommonSendMailUtils.sendMail(CommonSendMailUtils.MAIL_PROCESS_CTRIP_TYPE,
                                    reprot, reprot.getCcCwid(), mailType, reportExcelFile);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CommonSendMailUtils.sendMail(CommonSendMailUtils.MAIL_SENDER_CTRIP_CONST,
                remindSubject, remindUser, "Generate ctrip order report Total :   "+mailTotal,
                "");
        log.info("Generate ctrip order report end.");
    }

    private String getOrderReportFile(Map<String, Object> dataMap, String ccCode) {
        StringBuilder builder = new StringBuilder(orderReportPath);
        builder.append("BAYER_CTRIP_ORDER_REPORT_").append(ccCode).append("_").append(dataMap.get("startDate").toString().substring(0,10).replace("-", ""));
        builder.append("-").append(dataMap.get("endDate").toString().substring(0, 10).replace("-", "")).append(".xlsx");
        return builder.toString();
    }

    private void getLastMonthAllOrderInfo (Map<String, Object> dataMap) {
        applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-context.xml");
        CtripOrderInfoSyncBiz orderBiz = (CtripOrderInfoSyncBiz) applicationContext.getBean("ctripOrderInfoSyncBiz");

        String lastMonth = Convert.toStr(DateUtils.getFirstDayOfLastMonth()).substring(0, 7);
        String firstDay = Convert.toStr(dataMap.get("startDate")).substring(8, 10);
        String formDay = "";
        String endDay = "";
        int pageSize = 6;
        int total = DateUtils.getLastTotalDayOfLastMonth();
        int batchPage = total % pageSize == 0 ? total / pageSize : (int) (total / pageSize) + 1;
        log.info("Query ctrip order: total={}, batchPage={}", total, batchPage);

        dataMap.clear();
        for (int i = 0; i < batchPage; i++) {
            int fromIndex = i * pageSize;
            int toIndex = fromIndex + pageSize;
            firstDay = fromIndex == 0 ? firstDay : fromIndex + "";

            if (toIndex > total) {
                toIndex = total;
            }
            formDay = lastMonth + "-" + firstDay;
            endDay = lastMonth + "-" + (toIndex < 10 ? "0" + toIndex : toIndex);

            dataMap.put("start_date", formDay);
            dataMap.put("end_date", endDay);
            System.out.println(dataMap);
            orderBiz.processData(dataMap);
        }
    }

}
