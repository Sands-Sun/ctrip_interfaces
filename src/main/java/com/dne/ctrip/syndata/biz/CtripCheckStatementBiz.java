package com.dne.ctrip.syndata.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.dne.core.common.BaseException;
import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.EasyExcelUtil;
import com.dne.core.util.ExcelUtil;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.entity.CompanyCodeAndCostCenterRef;
import com.dne.ctrip.entity.DummyCostCenter;
import com.dne.ctrip.entity.OrderCheckStatementInfo;
import com.dne.ctrip.entity.StatementInfo;
import com.dne.ctrip.excel.model.OrderInfoRowModel;
import com.dne.ctrip.finance.entity.OrderCheckResultDetail;
import com.dne.ctrip.finance.service.OrderCheckStatementService;
import com.dne.ctrip.mail.handler.EmailReceiverHandler;
import com.dne.ctrip.mail.template.CommonSendMailUtils;
import com.dne.ctrip.mail.vo.StatementJobMailVo;
import com.dne.ctrip.syndata.service.CompanyService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dne.core.common.Constant.CTRIP_CHECK_STATEMENT_JOB_NAME;
import static com.dne.core.common.ErrorEnum.ORDER_STATEMENT_READ_FILE_ERROR;
import static com.dne.core.common.ErrorEnum.ORDER_STATEMENT_SEND_ISSUE_ORDER_EMAIL;

@Component
public class CtripCheckStatementBiz extends CtripAbsRiverBiz<StatementJobMailVo> {

    @Value("${email.bill.attachmentDir}")
    private String attachDir;

    @Value("${email.bill.appointSubject}")
    private String appointSubject;

    @Value("${order.check.email}")
    private String orderCheckEmail;

    @Autowired
    private EmailReceiverHandler handler;

    @Autowired
    private OrderCheckStatementService orderCheckStatementService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApplicationContext applicationContext;

    public CtripCheckStatementBiz() {
        super(CTRIP_CHECK_STATEMENT_JOB_NAME);
    }

    @Override
    protected void hookInit() {

    }

    @Override
    public void processData(Map<String, Object> dataMap,StatementJobMailVo mailVo) {
        // 1、从邮件读取每个月对账单excel文件
        List<String> billFileList = Lists.newArrayList();
        String billFile = (String) dataMap.get("billFile");
        if (null == billFile) {
            String attachPath = attachDir + "/" + batchNo + "/";
            List<String> attachList = handler.receiveMailAttachment(appointSubject, attachPath);
            billFileList.addAll(attachList);
        } else {
            billFileList.add(billFile);
        }

        log.info("Order statement files: " + billFileList);
        List<String> removeList = Lists.newArrayList();
        if(CollUtil.isNotEmpty(billFileList)){
            for (String filePath : billFileList) {
                String fileName = FileUtil.file(filePath).getName();
                String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
                if(!"xlsx".equalsIgnoreCase(prefix)){
                    removeList.add(filePath);
                }
            }
            billFileList.removeAll(removeList);
        }

        if(CollectionUtils.isNotEmpty(billFileList)){
            for (String billFilePath : billFileList) {
                try {
                    long statementId = this.saveOrderBillDetails(mailVo, billFilePath);
                    // 3、核对订单明细、按照三个维度：订单金额（对账单金额比本地多）、订单数量（对账单数量比本地多）、重复订单（对账单是否含有已经对过账的订单）
                    List<OrderCheckResultDetail> checkResultList = this.checkOrderStatement(mailVo,statementId);
                    if (!checkResultList.isEmpty()) {// 若存在核对不通过的订单，发邮件
                        this.sendEmail(dataMap, checkResultList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Check ctrip bill file save db fail.billFilePath="+billFilePath+"exception:"+e);
                    throw e;
                }
            }
        }else {
            log.info("No order statement file");
        }
    }

    private long saveOrderBillDetails(StatementJobMailVo mailVo, String billFilePath){
        // 保存对账主表信息
        StatementInfo statement = new StatementInfo(billFilePath, DateUtil.today(), 0, 0, "0", batchNo);
        orderCheckStatementService.addCheckStatement(statement);
        String billName= billFilePath.substring(billFilePath.length()-22);
        // 保存对账明细
        int orderNumTotal;
        int refundNumTotal;
        String statementDate = statement.getStatementDate();
        Long statementId = Convert.toLong(statement.getId());
        Map<String, Object> paramMap = Maps.newHashMap();
        Pair<List<OrderInfoRowModel>, Integer>  resultOrderMap;
        List<OrderInfoRowModel> lBaseRowModels;
        List<OrderInfoRowModel> lOrderRowModels;
        List<OrderInfoRowModel> lRefundRowModels;
        log.info("Order statement file: " + billFilePath);
        String billDate = DateUtil.thisYear()+ StringUtils.getNumberFromString(billName);
        paramMap.put("billDate", billDate);
        mailVo.setStatementDate(statementDate);
//        applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring-context.xml");
//        CtripCheckStatementBiz csBiz = (CtripCheckStatementBiz)applicationContext.getBean("ctripCheckStatementBiz");
        try (InputStream inputStream = Files.newInputStream(Paths.get(billFilePath));) {
            EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
            log.info("------------start process bill order info -----");
            resultOrderMap = easyExcelUtil.readExcel2007(inputStream,1,2, ExcelTypeEnum.XLSX, OrderInfoRowModel.class);
            lBaseRowModels = resultOrderMap.getKey();
        }catch (IOException e) {
            log.error("IOException: ",e);
            throw new BaseException(ORDER_STATEMENT_READ_FILE_ERROR.getCode(),
                    ORDER_STATEMENT_READ_FILE_ERROR.getMessage(),e);
        }
        List<String> cwIds = lBaseRowModels.stream().map(OrderInfoRowModel::getCallUserEmployeeId)
                .filter(StringUtils::isNotEmpty).distinct().collect(Collectors.toList());
        log.debug("lOrderRowModels cwIds size: {}", cwIds.size());
        List<CompanyCodeAndCostCenterRef> cwIdRefs = companyService.batchGetCompanyCodeByRefId(cwIds, "cwId", batchQueryCount);
        log.debug("Get CompanyCodeAndCostCenterRef from employeeInfo size: {}", cwIdRefs.size());
        Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap = cwIdRefs.stream().collect(
                Collectors.toMap(CompanyCodeAndCostCenterRef::getRefId, c -> c, (oldValue, newValue) -> newValue));

        lOrderRowModels = lBaseRowModels.stream().filter(o -> {
            String realPay = o.getCompanyRealPay();
            boolean isNum = NumberUtils.isNumber(realPay);
            boolean isNegativeNum = new BigDecimal(realPay).compareTo(BigDecimal.ZERO) >= 0;
            return  isNum && isNegativeNum;
        }).collect(Collectors.toList());

        lRefundRowModels = lBaseRowModels.stream().filter(o -> {
            String realPay = o.getCompanyRealPay();
            boolean isNum = NumberUtils.isNumber(realPay);
            boolean isNegativeNum = new BigDecimal(realPay).compareTo(BigDecimal.ZERO) < 0;
            return  isNum && isNegativeNum;
        }).collect(Collectors.toList());

        log.info("lRefundRowModels.size---------------"+lRefundRowModels.size());
        refundNumTotal = lRefundRowModels.size();
        this.saveRefundStatements(statementId,paramMap, lRefundRowModels,cwIdRefMap);

        log.info("lOrderRowModels.size---------------"+lOrderRowModels.size());
        orderNumTotal = lOrderRowModels.size();
        this.saveOrderStatements(statementId,paramMap, lOrderRowModels,cwIdRefMap);
        log.info("totalMap.get(\"orderNumTotal\")---------------"+orderNumTotal);
        log.info("totalMap.get(\"refundNumTotal\")---------------"+refundNumTotal);
        statement.setStatementId(statement.getId().intValue());
        statement.setOrderNum(orderNumTotal);
        statement.setRefundNum(refundNumTotal);
        statement.setCheckStatus("1");

        orderCheckStatementService.updCheckStatement(statement);
        mailVo.setOrderNumTotal(orderNumTotal);
        mailVo.setOrderRefundNumTotal(refundNumTotal);
        mailVo.setStatementFile(billFilePath);
        return statementId;
    }


    private void saveOrderStatements(Long statementId,Map<String, Object> paramMap,List<OrderInfoRowModel> lOrderRowModels,
                                     Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap) {
        List<DummyCostCenter> listDummy = orderCheckStatementService.getDummyCostCenters();
        DummyCostCenter dummyCostCenter = listDummy.stream().filter(b -> "0813".equals(b.getCompanyCode()))
                .findFirst().orElse(null);
        if(Objects.nonNull(dummyCostCenter)){
            List<OrderCheckStatementInfo> statements = convertRowModelToStatement(
                    statementId,dummyCostCenter.getDummyCoder(),lOrderRowModels,cwIdRefMap);
            List<List<OrderCheckStatementInfo>> partition = Lists.partition(statements, batchCount);
            log.info("Save sync order statement partition order info size: {}", partition.size());
            for(int i = 0; i < partition.size(); i++ ){
                List<OrderCheckStatementInfo> subRefundList = partition.get(i);
                log.info("Save partition of order statement: {}, subList count: {} ", i,subRefundList.size());
                paramMap.put("orders", subRefundList);
                orderCheckStatementService.addOrderStatements(paramMap);
            }
        }
    }

    /**
     * 保存对账单退款明细
     * @param paramMap
     */
    private void saveRefundStatements(Long statementId,Map<String, Object> paramMap, List<OrderInfoRowModel> lRefundRowModels,
                                      Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap) {
        List<OrderCheckStatementInfo> statements = convertRowModelToStatement(statementId,lRefundRowModels,cwIdRefMap);
        List<List<OrderCheckStatementInfo>> partition = Lists.partition(statements, batchCount);
        log.info("Save sync refund statement partition order info size: {}", partition.size());
        for(int i = 0; i < partition.size(); i++ ){
            List<OrderCheckStatementInfo> subRefundList = partition.get(i);
            log.info("Save partition of refund statement: {}, subList count: {} ", i,subRefundList.size());
            paramMap.put("refunds", subRefundList);
            orderCheckStatementService.addRefundStatements(paramMap);
        }

    }

    private List<OrderCheckStatementInfo> convertRowModelToStatement(
            Long statementId, String bclDummyCostCenter,List<OrderInfoRowModel> lOrderRowModels,
            Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap) {
        List<OrderCheckStatementInfo> statements = Lists.newArrayList();
        OrderCheckStatementInfo statementInfo;
        List<String> orderIds = lOrderRowModels.stream().filter(o -> StringUtils.isNotEmpty(o.getOrderId()))
                .distinct().map(OrderInfoRowModel::getOrderId).collect(Collectors.toList());
        log.debug("lOrderRowModels orderId size: {}", orderIds.size());
        List<String> centerCodes = lOrderRowModels.stream().filter(o -> StringUtils.isNotEmpty(o.getCostCenterName()))
                .distinct().map(OrderInfoRowModel::getCostCenterName).collect(Collectors.toList());
        log.debug("lOrderRowModels centerCodes size: {}", centerCodes.size());

        List<CompanyCodeAndCostCenterRef> orderIdRefs = companyService.batchGetCompanyCodeByRefId(orderIds,"orderId",batchQueryCount);
        log.debug("Get CompanyCodeAndCostCenterRef from orderInfo size: {}", orderIdRefs.size());
        List<CompanyCodeAndCostCenterRef> costCenterRefs =
                companyService.batchGetCompanyCodeByRefId(centerCodes,"centerCode", batchQueryCount);
        log.debug("Get CompanyCodeAndCostCenterRef from TCFG_CostCenter size: {}", centerCodes.size());

        Map<String, CompanyCodeAndCostCenterRef> orderIdRefMap = orderIdRefs.stream().collect(
                Collectors.toMap(CompanyCodeAndCostCenterRef::getRefId, c -> c, (oldValue, newValue) -> newValue));
        Map<String, CompanyCodeAndCostCenterRef> costCenterRefMap = costCenterRefs.stream().collect(
                Collectors.toMap(CompanyCodeAndCostCenterRef::getCostCenter, c -> c, (oldValue, newValue) -> newValue));

        for(OrderInfoRowModel orderInfoRowModel : lOrderRowModels){
            String companyCode = null;
            String costCenter = null;
            String orderId = orderInfoRowModel.getOrderId();
            String cwid = orderInfoRowModel.getCallUserEmployeeId();
            String ipin = orderInfoRowModel.getCallUserEmployeeIpin();
            String costCenterName = orderInfoRowModel.getCostCenterName();
            if(StringUtils.isEmpty(orderId)){
                log.debug("OrderId is empty {}", orderInfoRowModel);
                continue;
            }
            statementInfo = new OrderCheckStatementInfo();
            BeanUtils.copyProperties(orderInfoRowModel, statementInfo);
            statementInfo.setStatementId(statementId);
            statementInfo.setPayTime(statementInfo.getEndChargeTime());
            CompanyCodeAndCostCenterRef cwIdRef = cwIdRefMap.get(cwid);
            if(Objects.isNull(cwIdRef)){
                log.info("Get CompanyCodeAndCostCenterRef by ipin{}", ipin);
                cwIdRef = companyService.getCompanyCodeByIpin(ipin);
                cwIdRef = Objects.isNull(cwIdRef) ? new CompanyCodeAndCostCenterRef() : cwIdRef;
            }
            statementInfo.setEmailAddress(StringUtils.isNotEmpty(cwIdRef.getRefId()) ? cwIdRef.getExtraRef() : StringUtils.EMPTY);
            if(orderIdRefMap.containsKey(orderId)){
                CompanyCodeAndCostCenterRef orderIdRef = orderIdRefMap.get(orderId);
                companyCode = orderIdRef.getCompanyCode();
                costCenter = orderIdRef.getCostCenter();
                log.info("Fill in companyCode: {},  And costCenter: {} from order_info",companyCode,costCenter);
                if((StringUtils.isEmpty(costCenter) || StringUtils.isEmpty(companyCode))
                        && costCenterRefMap.containsKey(costCenterName)){
                    CompanyCodeAndCostCenterRef costCenterRef = costCenterRefMap.get(costCenterName);
                    companyCode = costCenterRef.getCompanyCode();
                    log.info("Fill in companyCode: {},  And costCenter: {} from TCFG_CostCenter",companyCode,costCenter);
                    if(StringUtils.isEmpty(companyCode)){
                        companyCode = cwIdRef.getCompanyCode();
                        costCenter = cwIdRef.getCostCenter();
                        log.info("Fill in companyCode: {},  And costCenter: {} from employeeInfo",companyCode,costCenter);
                    }
                }
            }else {
                companyCode = cwIdRef.getCompanyCode();
                costCenter = cwIdRef.getCostCenter();
                log.info("Fill in companyCode: {},  And costCenter: {} from employeeInfo",companyCode,costCenter);
            }
            statementInfo.setCompanyCode(StringUtils.isEmpty(companyCode) ? "0813" : companyCode);
            statementInfo.setCostCenter(StringUtils.isEmpty(costCenter) ? bclDummyCostCenter : costCenter);
           if("0813".equals(statementInfo.getCompanyCode()) && bclDummyCostCenter.equals(statementInfo.getCostCenter())){
               statementInfo.setCcTag("F");
           }
            statementInfo.setBatchNo(batchNo);
            statements.add(statementInfo);
        }
        return statements;
    }

    private List<OrderCheckStatementInfo> convertRowModelToStatement(
            Long statementId,List<OrderInfoRowModel> lRefundRowModels,Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap) {
        List<OrderCheckStatementInfo> statements = Lists.newArrayList();
        OrderCheckStatementInfo statementInfo;
        for(OrderInfoRowModel orderInfoRowModel : lRefundRowModels){
            statementInfo = new OrderCheckStatementInfo();
            BeanUtils.copyProperties(orderInfoRowModel, statementInfo);
            statementInfo.setStatementId(statementId);
            String cwid = orderInfoRowModel.getCallUserEmployeeId();
            CompanyCodeAndCostCenterRef cwIdRef = cwIdRefMap.getOrDefault(cwid, new CompanyCodeAndCostCenterRef());
            statementInfo.setEmailAddress(StringUtils.isNotEmpty(cwIdRef.getRefId()) ? cwIdRef.getExtraRef() : StringUtils.EMPTY);
            statementInfo.setBatchNo(batchNo);
            statements.add(statementInfo);
        }
        return statements;
    }


    /**
     * 核对对账单明细
     * @param mailVo
     */
    private List<OrderCheckResultDetail> checkOrderStatement(StatementJobMailVo mailVo,long statementId) {
        List<OrderCheckResultDetail> checkResultList = Lists.newArrayList();
        // 1、处理不存在的订单(check_result=1)
        List<OrderCheckResultDetail> noExistOrders = orderCheckStatementService.getCheckNoExistOrders(batchNo);
        log.info("Check statement no exist orders: " + noExistOrders);
        int noExistOrderNum = null == noExistOrders ? 0 : noExistOrders.size();
        mailVo.setNoExistOrderNum(noExistOrderNum);
        if (noExistOrderNum > 0) {
            for(OrderCheckResultDetail noExistOrder : noExistOrders) {
                orderCheckStatementService.updateCheckResultBySimple(noExistOrder);
            }
            checkResultList.addAll(noExistOrders);
        }

        // 2、处理重复订单(check_result=2)
        List<OrderCheckResultDetail> repeatOrders = orderCheckStatementService.getCheckRepeatOrders(batchNo);
        log.info("Check statement repeat orders: " + repeatOrders);
        int repeatOrderNum = null == repeatOrders ? 0 : repeatOrders.size();
        mailVo.setRepeatOrderNum(repeatOrderNum);
        if (repeatOrderNum > 0) {
            for(OrderCheckResultDetail repeatOrder : repeatOrders) {
                orderCheckStatementService.updateCheckResultBySimple(repeatOrder);
            }
            checkResultList.addAll(repeatOrders);
        }

        // 3、处理对账单订单金额大于同步订单金额(check_result=3)
        List<OrderCheckResultDetail> largeAmtOrders = orderCheckStatementService.getCheckLargeAmtOrders(batchNo);
        log.info("Check statement large amt orders: " + largeAmtOrders);
        int largeAmtOrderNum = null == largeAmtOrders ? 0 : largeAmtOrders.size();
        mailVo.setLargeAmtOrderNum(largeAmtOrderNum);
        if (largeAmtOrderNum > 0) {
            for(OrderCheckResultDetail largeAmtOrder : largeAmtOrders) {
                orderCheckStatementService.updateCheckResultBySimple(largeAmtOrder);
            }
            checkResultList.addAll(largeAmtOrders);
        }
        return checkResultList;
    }


    /**
     * 发送邮件
     * @param dataMap
     * @param checkResultList
     */
    private void sendEmail(Map<String, Object> dataMap, List<OrderCheckResultDetail> checkResultList) {
        String statementDate = (String) dataMap.get("statementDate");
        String date = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        String title = "携程对账有问题订单明细";
        String subject = "携程对账单-有问题的订单";
        String mailBody = "携程对账有问题订单，请参考附件！";
        String fileName = title+"_"+date+ ".xlsx";
        String filePath = attachDir+statementDate + "/";
        String orderCheckFile = filePath + fileName;
        LinkedHashMap<String, List> excelData = new LinkedHashMap<>();
        excelData.put(statementDate, checkResultList);
        List<String[]> headNames = new ArrayList<String[]>();
        headNames.add(new String[] { "专车订单Id",	"订单成本中心", "员工成本中心","公司实际支付金额", "支付时间", "问题描述"});
        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] {"orderId", "costCenter" ,"userCostCenter" ,"orderAmt", "payTime", "checkDesc"});
        File file = FileUtil.file(filePath,fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        try {
            OutputStream output = Files.newOutputStream(file.toPath());
            ExcelUtil.ExportSetInfo setInfo = new ExcelUtil.ExportSetInfo();
            setInfo.setObjsMap(excelData);
            setInfo.setFieldNames(fieldNames);
            setInfo.setTitles(new String[] {title});
            setInfo.setHeadNames(headNames);
            setInfo.setOut(output);
            com.dne.core.util.ExcelUtil.export2ExcelByPojo(setInfo);
            log.info("Generate didi order send email: " + orderCheckEmail);
            CommonSendMailUtils.sendMail(subject, orderCheckEmail, mailBody, orderCheckFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成“"+orderCheckFile+"”，失败！异常信息："+e);
            throw new BaseException(ORDER_STATEMENT_SEND_ISSUE_ORDER_EMAIL.getCode(),
                    ORDER_STATEMENT_SEND_ISSUE_ORDER_EMAIL.getMessage(),e);
        }

    }

}
