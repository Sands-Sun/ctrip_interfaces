package com.dne.ctrip.syndata.biz;

import com.dne.core.common.Constant;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CtripEmployeeInfoSyncBizV1 extends CtripEmployeeInfoSyncBiz{


    @Override
    public String processLog(Map<String, Object> dataMap) {
        StringBuilder synLog = new StringBuilder();
        synLog.append("<b>Ctrip Employee synchronization results</b>").append("\r\n\r\n");
        Integer beforeSyncHREmployeeCount = (Integer)dataMap.getOrDefault("beforeSyncHREmployeeCount", 0);
        Integer afterSyncHREmployeeCount = (Integer)dataMap.getOrDefault("afterSyncHREmployeeCount", 0);
        synLog.append("Before HR Employee information Count: ").append(beforeSyncHREmployeeCount).append("\r\n");
        synLog.append("After HR Employee information Count: ").append(afterSyncHREmployeeCount).append("\r\n");
        synLog.append("Sync employee total: ").append(dataMap.get("totalSyncSize")).append("\r\n");
        synLog.append("Sync new create employee total: ").append(dataMap.get("SyncNewSize")).append("\r\n");
        synLog.append("Sync modify employee total: ").append(dataMap.get("SyncModifySize")).append("\r\n");
        synLog.append("Sync delete employee total: ").append(dataMap.get("SyncDeleteSize"));
        return synLog.toString();
    }

    @Override
    public void processData(Map<String, Object> dataMap) {
        log.info("file.encoding=" + System.getProperty("file.encoding"));
        syncEmployeeInfoFromHRCentral(dataMap);
        //同步员工信息到携程API错误
        if(Objects.nonNull(jobStatus)
                && jobStatus.getRunStatus().equals(Constant.CTRIP_JOB_FAIL_STATUS)
                && jobStatus.getErrorCode() == 1000) {
            String pervBatchNo = jobStatus.getBatchId();
            log.info("Previous Employee information batchNo: " + pervBatchNo);
            log.info("Previous job run error...");
            log.info("Previous job batch no: {}",pervBatchNo);
            handelEmployeeInfo(pervBatchNo, false,dataMap);
        }else {
            //jobStatus.getRunStatus().equals(Constant.CTRIP_JOB_SUCCESS_STATUS)
            initEmployeeInfoFromExcelFile();
            employeeService.syncEmployeeInfo(batchNo);
            handelEmployeeInfo(batchNo, true,dataMap);
        }
    }

    private void handelEmployeeInfo(String batchNo, boolean syncFlag, Map<String, Object> dataMap){
        List<SyncEmployeeInfo> syncEmployeeInfoList = employeeService.getSyncEmployeeInfoByBatchNo(batchNo);
        if(!syncFlag){
            syncEmployeeInfoList = syncEmployeeInfoList.stream().filter(
                    e -> e.getSyncStatus().equals(Constant.CTRIP_SYNC_STATUS_N)).collect(Collectors.toList());
        }
        dataMap.put("totalSyncSize", syncEmployeeInfoList.size());
        if(CollectionUtils.isNotEmpty(syncEmployeeInfoList)){
            List<SyncEmployeeInfo> prepareSyncResult = preparePostEmployeeInfo(syncEmployeeInfoList,dataMap);
            postEmployeeIntoToCtrip(batchNo,prepareSyncResult);
        }
    }


    private List<SyncEmployeeInfo> preparePostEmployeeInfo(List<SyncEmployeeInfo> syncEmployeeInfoList,Map<String, Object> dataMap) {
        List<SyncEmployeeInfo> result = Lists.newArrayList();
        List<SyncEmployeeInfo> newCreateEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(Constant.CTRIP_OPREATE_CREATE_STATUS))
                .distinct().collect(Collectors.toList());

        List<SyncEmployeeInfo> modifiedEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(Constant.CTRIP_OPREATE_MODIFY_STATUS))
                .distinct().collect(Collectors.toList());

        List<SyncEmployeeInfo> deletedEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(Constant.CTRIP_OPREATE_DELETE_STATUS))
                .filter(e -> "Y".equals(e.getMarkDeleted()))
                .distinct().collect(Collectors.toList());


        if(CollectionUtils.isNotEmpty(newCreateEmployeeList)){
            log.info("New create employee size: {}", newCreateEmployeeList.size());
            result.addAll(newCreateEmployeeList);
        }
        if(CollectionUtils.isNotEmpty(modifiedEmployeeList)){
            log.info("Modify employee size: {}", modifiedEmployeeList.size());
            result.addAll(modifiedEmployeeList);
        }
        if(CollectionUtils.isNotEmpty(deletedEmployeeList)){
            log.info("Delete employee size: {}", deletedEmployeeList.size());
            result.addAll(deletedEmployeeList);
        }
        log.debug("Before filter change syncEmployeeInfo size: {}", syncEmployeeInfoList.size());
        log.debug("After filter change syncEmployeeInfo size: {}", result.size());

        dataMap.put("SyncNewSize",newCreateEmployeeList.size());
        dataMap.put("SyncModifySize",modifiedEmployeeList.size());
        dataMap.put("SyncDeleteSize",deletedEmployeeList.size());
        return result;
    }
}
