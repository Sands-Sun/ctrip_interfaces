package com.dne.ctrip.syndata.biz;

import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.ctrip.mail.vo.EmployeeSyncJobMailVo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dne.core.common.Constant.CTRIP_JOB_FAIL_STATUS;
import static com.dne.core.common.Constant.CTRIP_OPREATE_CREATE_STATUS;
import static com.dne.core.common.Constant.CTRIP_OPREATE_DELETE_STATUS;
import static com.dne.core.common.Constant.CTRIP_OPREATE_MODIFY_STATUS;
import static com.dne.core.common.Constant.CTRIP_SYNC_STATUS_N;


@Component
public class CtripEmployeeInfoSyncBizV1 extends CtripEmployeeInfoSyncBiz{



    @Override
    public void processData(Map<String,Object> paramMap, EmployeeSyncJobMailVo mailVo) {
        log.info("file.encoding=" + System.getProperty("file.encoding"));
        //同步员工信息到携程API错误
        if(Objects.nonNull(jobStatus)
                && jobStatus.getRunStatus().equals(CTRIP_JOB_FAIL_STATUS)
                && jobStatus.getErrorCode() == 1000) {
            String pervBatchNo = jobStatus.getBatchId();
            log.info("Previous Employee information batchNo: " + pervBatchNo);
            log.info("Previous job run error...");
            log.info("Previous job batch no: {}",pervBatchNo);
            handelEmployeeInfo(pervBatchNo, false,mailVo);
        }else {
            //jobStatus.getRunStatus().equals(Constant.CTRIP_JOB_SUCCESS_STATUS)
            syncEmployeeInfoFromHRCentral(mailVo);
            initEmployeeInfoFromExcelFile();
            employeeService.syncEmployeeInfo(batchNo);
            handelEmployeeInfo(batchNo, true,mailVo);
        }
        //保存信息员工数据到excel
        exportEmployeeInfoChangedToFile(mailVo);
    }

    private void handelEmployeeInfo(String batchNo, boolean syncFlag, EmployeeSyncJobMailVo mailVo){
        List<SyncEmployeeInfo> syncEmployeeInfoList = employeeService.getSyncEmployeeInfoByBatchNo(batchNo);
        if(!syncFlag){
            syncEmployeeInfoList = syncEmployeeInfoList.stream().filter(
                    e -> e.getSyncStatus().equals(CTRIP_SYNC_STATUS_N)).collect(Collectors.toList());
        }
        mailVo.setTotalSync(syncEmployeeInfoList.size());
        if(CollectionUtils.isNotEmpty(syncEmployeeInfoList)){
            List<SyncEmployeeInfo> prepareSyncResult = preparePostEmployeeInfo(syncEmployeeInfoList,mailVo);
            postEmployeeIntoToCtrip(batchNo,prepareSyncResult);
        }
    }


    private List<SyncEmployeeInfo> preparePostEmployeeInfo(List<SyncEmployeeInfo> syncEmployeeInfoList, EmployeeSyncJobMailVo mailVo) {
        List<SyncEmployeeInfo> result = Lists.newArrayList();
        newCreateEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(CTRIP_OPREATE_CREATE_STATUS))
                .distinct().collect(Collectors.toList());

        modifiedEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(CTRIP_OPREATE_MODIFY_STATUS))
                .distinct().collect(Collectors.toList());

        deletedEmployeeList = syncEmployeeInfoList.stream()
                .filter(e -> e.getOperateStatus().equals(CTRIP_OPREATE_DELETE_STATUS))
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
        mailVo.setTotalSyncNewCreate(newCreateEmployeeList.size());
        mailVo.setTotalSyncModified(modifiedEmployeeList.size());
        mailVo.setTotalSyncDeleted(deletedEmployeeList.size());
        return result;
    }
}
