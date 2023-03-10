package com.dne.ctrip.syndata.biz;

import com.dne.core.common.BaseException;
import com.dne.core.common.Constant;
import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.DateUtils;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.domain.*;
import com.dne.ctrip.entity.CompanyCodeAndCostCenterRef;
import com.dne.ctrip.entity.OrderInfo;
import com.dne.ctrip.mail.vo.OrderInfoSyncJobMailVo;
import com.dne.ctrip.param.CarOrderSettlementSearchRequest;
import com.dne.ctrip.syndata.service.CompanyService;
import com.dne.ctrip.syndata.service.OrderInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dne.core.common.Constant.CTRIP_SYNC_ORDER_JOB_NAME;

@Component
public class CtripOrderInfoSyncBiz extends CtripAbsRiverBiz<OrderInfoSyncJobMailVo> {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private CompanyService companyService;


    public CtripOrderInfoSyncBiz() {
        super(CTRIP_SYNC_ORDER_JOB_NAME);
    }

    @Override
    protected void hookInit() {
        //TODO hook init
    }

    @Override
    public void processData(Map<String, Object> dataMap,OrderInfoSyncJobMailVo mailVo) {
        log.info("Synchronize ctrip order batchNo: " + batchNo);
        String startDate = (String) dataMap.get("start_date");
        String endDate = (String) dataMap.get("end_date");
        log.debug("start_date={}, end_date={}",startDate,endDate);
//        mockCarOrderSettlement();

        // 1、根据日期查询Ctrip订单插入到订单同步表（b_ctrip_sync_order）中
        int syncOrderTotal = this.processCtripiRiverOrder(
                DateUtils.strToDate(startDate),
                DateUtils.strToDate(endDate));
        log.info("Synchronize ctrip order total: " + syncOrderTotal);
        // 2、把同步订单数据和本地订单数据比较，若订单Id存在，更新本地订单，若不存在，插入订单
        int addOrderNum = orderInfoService.addOrderInfo(batchNo);
        log.info("Synchronize didi order add num: " + addOrderNum);

        // 查询需要更新的订单
        List<OrderInfo> orderList = orderInfoService.getUpdSyncOrderInfo(batchNo);
        int udpOrderNum = orderList.size();
        log.info("Synchronize didi order update num: " + udpOrderNum);
        if (udpOrderNum > 0) {
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("batchNo", batchNo);
            List<List<OrderInfo>> partition =
                    Lists.partition(orderList, batchCount);
            log.info("Update order partition order info size: {}", partition.size());
            for(int i = 0; i < partition.size(); i++ ){
                List<OrderInfo> subOrderInfo = partition.get(i);
                log.info("Update partition of order info: {}, subList count: {} ", i,subOrderInfo.size());
                paramMap.put("orders", subOrderInfo);
                orderInfoService.updOrderInfo(paramMap);
            }
        }
        mailVo.setStartDate(startDate);
        mailVo.setEndDate(endDate);
        mailVo.setSyncOrderTotal(syncOrderTotal);
        mailVo.setAddOrderNum(addOrderNum);
        mailVo.setUpdateOrderNum(udpOrderNum);
        log.info("Synchronize didi order end.");
    }


    private int processCtripiRiverOrder(Date startDate, Date endDate) {
        int totalRecord;
        CarOrderSettlementSearchRequest param =
                new CarOrderSettlementSearchRequest(startDate,endDate,1,ctripPageSize);
        CarOrderSettlementSearchResponse searchResponse =  handler.getOrderCarList(param);
        ResponseStatus responseStatus = searchResponse.getStatus();
        if(!responseStatus.getSuccess()){
            throw new BaseException(responseStatus.getErrorCode(), responseStatus.getMessage());
        }else {
            List<CarOrderAccountSettlementDetail> details = searchResponse.getCarOrderAccountSettlementDetailList();
            totalRecord = searchResponse.getTotalRecord();
            int totalPage = totalRecord % ctripPageSize == 0 ? totalRecord / ctripPageSize : (totalRecord / ctripPageSize) + 1;
            log.info("Query ctrip order: totalRecord={}, totalPage={}", totalRecord, totalPage);
            // 保存携程平台订单信息到本地订单同步表中
            this.saveSyncOrderInfo(details);
            if (totalPage > 1) {
                for (int i = 1; i <= totalPage; i++) {
                    param.setPageIndex(i * ctripPageSize);
                    log.info("Query ctrip order offset: " + param.getPageIndex());
                    searchResponse = handler.getOrderCarList(param);
                    responseStatus = searchResponse.getStatus();
                    if(!responseStatus.getSuccess()){
                        throw new BaseException(responseStatus.getErrorCode(), responseStatus.getMessage());
                    }else {
                        details = searchResponse.getCarOrderAccountSettlementDetailList();
                        this.saveSyncOrderInfo(details);
                    }
                }
            }
        }
        log.info("totalRecord={}", totalRecord);
        return totalRecord;
    }


    private void saveSyncOrderInfo(List<CarOrderAccountSettlementDetail> details) {
        if(CollectionUtils.isEmpty(details)){
            log.info("Empty CarOrderAccountSettlementDetail...");
            return;
        }
        List<CarOrderPassengerInfo> passengerInfoList = details.stream().flatMap(d -> d.getCarOrderSettlementDetails().stream()
                .map(c -> c.getCarOrderDetail().getCarOrderPassengerInfoList().get(0))).collect(Collectors.toList());
        log.info("Passenger information size: {}", passengerInfoList.size());

        List<String> cwIds = passengerInfoList.stream().map(CarOrderPassengerInfo::getEmployeeID).distinct().collect(Collectors.toList());
        log.info("Passenger information cwIds size: {}", cwIds.size());
        List<String> centerCodes = passengerInfoList.stream().map(CarOrderPassengerInfo::getCostCenter1).collect(Collectors.toList());
        log.info("Passenger information costCenter size: {}", cwIds.size());

        List<CompanyCodeAndCostCenterRef> cwIdRefs =
                companyService.batchGetCompanyCodeByRefId(cwIds, "cwId", batchQueryCount);
//        List<CompanyCodeAndCostCenterRef> cwIdRefs = companyDao.getCompanyCodeByCwIds(cwIds);
        log.info("Get CompanyCodeAndCostCenterRef from employeeInfo CWID size: {}", cwIdRefs.size());
        List<CompanyCodeAndCostCenterRef> costCenterRefs =
                companyService.batchGetCompanyCodeByRefId(centerCodes, "centerCode", batchQueryCount);
//        List<CompanyCodeAndCostCenterRef> costCenterRefs = companyDao.getCompanyCodeByCenterCodes(centerCodes);
        log.info("Get CompanyCodeAndCostCenterRef from TCFG_CostCenter size: {}", centerCodes.size());

        Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap = cwIdRefs.stream().collect(
                Collectors.toMap(CompanyCodeAndCostCenterRef::getRefId, c -> c, (oldValue, newValue) -> newValue));
        log.info("Convert CompanyCodeAndCostCenterRef Map from employeeInfo CWID size: {}", cwIdRefMap.size());
        Map<String, CompanyCodeAndCostCenterRef> costCenterRefMap = costCenterRefs.stream().collect(
                Collectors.toMap(CompanyCodeAndCostCenterRef::getCostCenter, c -> c, (oldValue, newValue) -> newValue));
        log.info("Convert CompanyCodeAndCostCenterRef Map from TCFG_CostCenter size: {}", costCenterRefs.size());

        List<OrderInfo> orders = convertToOrderInfo(details,cwIdRefMap,costCenterRefMap);
        List<List<OrderInfo>> partition =
                Lists.partition(orders, batchCount);
        log.info("Save sync order partition order info size: {}", partition.size());
        for(int i = 0; i < partition.size(); i++ ){
            List<OrderInfo> subOrderInfo = partition.get(i);
            log.info("Save partition of order info: {}, subList count: {} ", i,subOrderInfo.size());
            orderInfoService.addSyncOrder(subOrderInfo);
        }
    }




    private List<OrderInfo> convertToOrderInfo(
            List<CarOrderAccountSettlementDetail> details,Map<String, CompanyCodeAndCostCenterRef> cwIdRefMap,
            Map<String, CompanyCodeAndCostCenterRef> costCenterRefMap) {
        List<OrderInfo> orders = Lists.newArrayList();
        OrderInfo orderInfo;
        log.debug("SettlementDetail size: {}", details.size());
        for(CarOrderAccountSettlementDetail detail : details){
            if(CollectionUtils.isNotEmpty(detail.getCarOrderSettlementDetails())){
                for(CarOrderSettlementDetail settDetail : detail.getCarOrderSettlementDetails()){
                    orderInfo = new OrderInfo();
                    CarOrderSettlementBaseInfo settlementBaseInfo = settDetail.getCarOrderSettlementBaseInfo();
                    CarOrderDetail orderDetail = settDetail.getCarOrderDetail();
                    CarOrderBaseInfo orderBaseInfo = orderDetail.getCarOrderBaseInfo();
                    CarOrderQuickProductInfo productInfo = orderDetail.getQuickProductInfo();
                    List<CarOrderPassengerInfo> passengerInfos = orderDetail.getCarOrderPassengerInfoList();
                    if(Objects.nonNull(settlementBaseInfo)){
                        orderInfo.setCreateTime(settlementBaseInfo.getCreateTime());
                        orderInfo.setOrderId(settlementBaseInfo.getOrderId());
                        orderInfo.setIsInvoice(settlementBaseInfo.getChecked());
                        orderInfo.setDelType(settlementBaseInfo.getDelType());
                        orderInfo.setTotalPrice(settlementBaseInfo.getRealAmount());
                        orderInfo.setActualPrice(settlementBaseInfo.getRealAmountHasPost());

                        orderInfo.setServicesFee(settlementBaseInfo.getServerFee());
                        orderInfo.setPostServiceFree(settlementBaseInfo.getPostServiceFee());
                        orderInfo.setExpressFee(settlementBaseInfo.getExpressFee());
                        orderInfo.setCarValueAddFee(settlementBaseInfo.getCarValueAddFee());
                        orderInfo.setCarAddTaxAmount(settlementBaseInfo.getCarAddTaxAmount());
                        orderInfo.setPenaltyFee(settlementBaseInfo.getPenaltyFee());
                    }
                    if(Objects.nonNull(orderBaseInfo)){
                        orderInfo.setCallPhone(orderBaseInfo.getContactMobile());
                        orderInfo.setStatus(orderBaseInfo.getOrderStatus());
                        orderInfo.setPayType(orderBaseInfo.getPaymentType());
                        orderInfo.setUseCarType(orderBaseInfo.getOrderType());
                        orderInfo.setCompanyPay(orderBaseInfo.getAccntAmount());
                        orderInfo.setPersonalPay(orderBaseInfo.getPersonAmount());
                    }
                    if(Objects.nonNull(productInfo)){
                        orderInfo.setRequireLevel(productInfo.getVehicleId());
                        orderInfo.setCity(productInfo.getDepartureCityId());
                        orderInfo.setCityName(productInfo.getDepartureCityName());
                        orderInfo.setStartName(productInfo.getDepartureAddressDetail());
                        orderInfo.setEndName(productInfo.getArrivalAddressDetail());
                        orderInfo.setNormalDistance(productInfo.getNormalDistance());
                        orderInfo.setDepartureTime(productInfo.getServiceBeginTime());
                        orderInfo.setFinishTime(productInfo.getServiceEndTime());
                        orderInfo.setPayTime(productInfo.getServiceEndTime());
                    }
                    if(CollectionUtils.isNotEmpty(passengerInfos)){
                        CarOrderPassengerInfo passengerInfo = passengerInfos.get(0);
                        String cwid = passengerInfo.getEmployeeID();
                        String costCenter = passengerInfo.getCostCenter1();
                        String companyCode;
                        orderInfo.setMemberId(cwid);
                        orderInfo.setPassengerPhone(passengerInfo.getPassengerPhone());
                        if(costCenterRefMap.containsKey(costCenter)){
                            CompanyCodeAndCostCenterRef costCenterRef = costCenterRefMap.get(costCenter);
                            companyCode = costCenterRef.getCompanyCode();
                            costCenter = costCenterRef.getCostCenter();
                            log.info("Fill in companyCode: {},  And costCenter: {} from TCFG_CostCenter",companyCode,costCenter);
                        }else {
                            CompanyCodeAndCostCenterRef cwIdRef = cwIdRefMap.getOrDefault(cwid, new CompanyCodeAndCostCenterRef());
                            companyCode = StringUtils.isEmpty(cwIdRef.getCompanyCode()) ? passengerInfo.getDept1() : cwIdRef.getCompanyCode();
                            costCenter = StringUtils.isEmpty(cwIdRef.getCostCenter()) ? costCenter : cwIdRef.getCostCenter();
                            log.info("Fill in companyCode: {},  And costCenter: {} from employeeInfo",companyCode,costCenter);
                        }
                        orderInfo.setCompanyCode(companyCode);
                        orderInfo.setCostCenter(costCenter);
                    }
                    orderInfo.setBatchNo(batchNo);
                    orders.add(orderInfo);
                }
            }
        }
        return orders;
    }
}
