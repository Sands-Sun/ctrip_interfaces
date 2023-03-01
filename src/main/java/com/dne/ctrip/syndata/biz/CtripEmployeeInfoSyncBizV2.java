package com.dne.ctrip.syndata.biz;

import com.dne.core.common.Constant;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.entity.EmployeeInfo;
import com.dne.ctrip.entity.EmployeeRankName;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.hrcentral.entity.HRCentralEmployee;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CtripEmployeeInfoSyncBizV2 extends CtripEmployeeInfoSyncBiz{



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
        if(Objects.nonNull(jobStatus)
                && jobStatus.getRunStatus().equals(Constant.CTRIP_JOB_FAIL_STATUS)
                && jobStatus.getErrorCode() == 1000) {
            String pervBatchNo = jobStatus.getBatchId();
            log.info("Previous Employee information batchNo: " + pervBatchNo);
            log.info("Previous job run error...");
            log.info("Previous job batch no: {}",pervBatchNo);
            List<SyncEmployeeInfo> syncEmployeeInfoList = employeeService.getSyncEmployeeInfoByBatchNo(pervBatchNo);
            List<SyncEmployeeInfo> errorSyncList = syncEmployeeInfoList.stream().filter(
                    e -> e.getSyncStatus().equals(Constant.CTRIP_SYNC_STATUS_N)).collect(Collectors.toList());
            dataMap.put("totalSyncSize", errorSyncList.size());
            if(CollectionUtils.isNotEmpty(errorSyncList)){
                postEmployeeIntoToCtrip(pervBatchNo,errorSyncList);
            }

        } else {
            initEmployeeInfoFromExcelFile();
            List<SyncEmployeeInfo> saveSyncEmployeeInfoList = Lists.newArrayList();
            //获取新增员工记录
            List<SyncEmployeeInfo> newCreateEmployees = convertNewCreateToSyncEmployee(rankNameMap,dataMap);
            if(CollectionUtils.isNotEmpty(newCreateEmployees)){
                log.info("New create employee size: {}", newCreateEmployees.size());
                employeeService.batchSaveSyncEmployeeInfo(newCreateEmployees, batchCount);
                saveSyncEmployeeInfoList.addAll(newCreateEmployees);
            }
            //获取员工更改信息记录 && 离职员工信息记录
            List<SyncEmployeeInfo> changedEmployees = convertChangedToSyncEmployee(currentHREmployeeInfoList,rankNameMap,dataMap);
            if(CollectionUtils.isNotEmpty(changedEmployees)){
                log.info("Change employee size: {}", newCreateEmployees.size());
                employeeService.batchSaveSyncEmployeeInfo(newCreateEmployees, batchCount);
                saveSyncEmployeeInfoList.addAll(changedEmployees);
            }
            dataMap.put("totalSyncSize", saveSyncEmployeeInfoList.size());
            postEmployeeIntoToCtrip(batchNo,saveSyncEmployeeInfoList);
            employeeService.saveOrUpdateEmployeeInfo(batchNo);
        }

    }

    private List<SyncEmployeeInfo> convertNewCreateToSyncEmployee(Map<String, String> rankNameMap,Map<String, Object> dataMap) {
        List<EmployeeInfo> employeeInfoList = employeeService.getEmployeeInfoByType("create");
        List<SyncEmployeeInfo> syncEmployeeInfoList = Lists.newArrayList();
        SyncEmployeeInfo syncEmployeeInfo;
        int createTotal = 0;
        for(EmployeeInfo employeeInfo : employeeInfoList){
            syncEmployeeInfo = new SyncEmployeeInfo();
            String cwid = employeeInfo.getCwid();
            String email = employeeInfo.getEmail();
            if(email.indexOf("ext@") > 0){
                log.info("Ignore external employee email: {}", employeeInfo);
                continue;
            }
            String rankName = rankNameMap.getOrDefault(cwid, "Staffs");
            BeanUtils.copyProperties(employeeInfo, syncEmployeeInfo);
            syncEmployeeInfo.setBatchNo(batchNo);
            syncEmployeeInfo.setOperateStatus(Constant.CTRIP_OPREATE_CREATE_STATUS);
            syncEmployeeInfo.setRankName(rankName);
            syncEmployeeInfoList.add(syncEmployeeInfo);
            createTotal = createTotal + 1;
        }
        dataMap.put("SyncNewSize",createTotal);
        return syncEmployeeInfoList;
    }

    private List<SyncEmployeeInfo> convertChangedToSyncEmployee(
            List<HRCentralEmployee> currentHREmployees, Map<String, String> rankNameMap,Map<String, Object> dataMap) {
        List<EmployeeInfo> employeeInfoList = employeeService.getEmployeeInfoByType("change");
        if(CollectionUtils.isEmpty(employeeInfoList)){
            return Collections.emptyList();
        }
        return compareChangeEmployees(currentHREmployees, employeeInfoList, rankNameMap,dataMap);
    }


    private List<SyncEmployeeInfo> compareChangeEmployees(
            List<HRCentralEmployee> currentHREmployees, List<EmployeeInfo> changeEmployees,
            Map<String, String> rankNameMap,Map<String, Object> dataMap) {
        List<SyncEmployeeInfo> syncEmployeeInfoList = Lists.newArrayList();
        SyncEmployeeInfo syncEmployeeInfo;
        int delTotal = 0;
        int modifyTotal = 0;
        for(HRCentralEmployee hrCentralEmployee : currentHREmployees){
            for(EmployeeInfo employeeInfo : changeEmployees){
                String hrCwid = hrCentralEmployee.getCwid();
                if(Objects.nonNull(hrCwid) && hrCwid.trim().equals(employeeInfo.getCwid().trim())){
                    syncEmployeeInfo = new SyncEmployeeInfo();
                    syncEmployeeInfo.setBatchNo(batchNo);
                    String hrEmployeeMarkDel = "1".equals(hrCentralEmployee.getDelFlag()) ? "Y" : "N";
                    Date hrLeavingDate = hrCentralEmployee.getLeavingDate();
                    String rankName = rankNameMap.getOrDefault(hrCwid, "Staffs");
                    String hrCompanyCode = hrCentralEmployee.getCompanyCode();
                    String hrOrgId = String.valueOf(hrCentralEmployee.getOrgId());
                    String hrOrgTxt = hrCentralEmployee.getOrgTxt();
                    String hrPositionId = hrCentralEmployee.getPositionId();
                    String hrPositionTxt = hrCentralEmployee.getPositionTxt();
                    String hrCostCenter = hrCentralEmployee.getCostCenter();
                    String hrEmailAddress = hrCentralEmployee.getEmail();
                    String hrFirstName = hrCentralEmployee.getBiFirstName();
                    String hrLastName = hrCentralEmployee.getBiLastName();
                    String hrElLastName = hrCentralEmployee.getElLastName();
                    String hrElFirstName = hrCentralEmployee.getElFirstName();
                    String hrChineseName;
                    if(StringUtils.containsChinese(hrElFirstName) || StringUtils.containsChinese(hrElLastName)){
                         hrChineseName = StringUtils.trim(hrElLastName) + StringUtils.trim(hrElFirstName);
                    }else {
                         hrChineseName = StringUtils.trim(hrElLastName) +StringUtils.SPACE + StringUtils.trim(hrElFirstName);
                         log.info("-----HrCentral not Chinese name: {}" + hrChineseName);
                    }

                    String hrWorkingLocation = hrCentralEmployee.getWorkingLocation();
                    String hrEmployeeGender = "1".equals(hrCentralEmployee.getGender()) ? "Male" : "Female";
                    if("Y".equals(hrEmployeeMarkDel) &&
                            !hrEmployeeMarkDel.equals(employeeInfo.getMarkDeleted())){
                        // delete
                        BeanUtils.copyProperties(hrCentralEmployee, syncEmployeeInfo);
                        syncEmployeeInfo.setSfUserId(hrCentralEmployee.getUserId());
                        syncEmployeeInfo.setChineseName(hrChineseName);
                        syncEmployeeInfo.setFirstName(hrFirstName);
                        syncEmployeeInfo.setLastName(hrLastName);
                        syncEmployeeInfo.setEmail(hrEmailAddress);
                        syncEmployeeInfo.setLocation(hrWorkingLocation);
                        syncEmployeeInfo.setRankName(rankName);
                        syncEmployeeInfo.setOrgId(hrOrgId);
                        syncEmployeeInfo.setMarkDeleted(hrEmployeeMarkDel);
                        syncEmployeeInfo.setDimissionDate(hrLeavingDate);
                        syncEmployeeInfo.setGender(hrEmployeeGender);
                        syncEmployeeInfo.setOperateStatus(Constant.CTRIP_OPREATE_DELETE_STATUS);
                        syncEmployeeInfo.setMarkDeleted(hrEmployeeMarkDel);
                        syncEmployeeInfoList.add(syncEmployeeInfo);
                        delTotal = delTotal + 1;
                        log.info("Delete employee cwid ****: {}", syncEmployeeInfo.getCwid());
                        continue;
                    }

                   boolean genderChange = !hrEmployeeGender.equals(employeeInfo.getGender());
                   boolean companyCodeChange = !hrCompanyCode.equals(employeeInfo.getCompanyCode());
                   boolean orgIdChange = !hrOrgId.equals(employeeInfo.getOrgId());
                   boolean orgTxtChange = !hrOrgTxt.equals(employeeInfo.getOrgTxt());
                   boolean positionIdChange = !hrPositionId.equals(employeeInfo.getPositionId());
                   boolean positionTextChange = !hrPositionTxt.equals(employeeInfo.getPositionTxt());
                   boolean costCenterChange = !hrCostCenter.equals(employeeInfo.getCostCenter());
                   boolean emailChange = !hrEmailAddress.equals(employeeInfo.getEmail());
                   boolean firstNameChange = !hrFirstName.equals(employeeInfo.getFirstName());
                   boolean lastNameChange = !hrLastName.equals(employeeInfo.getLastName());
                   boolean chineseNameChange = !hrChineseName.equals(employeeInfo.getChineseName());
                   boolean workingLocationChange = !hrWorkingLocation.equals(employeeInfo.getLocation());
                   boolean rankNameChange = !"".equals(rankName) && !rankName.equals(employeeInfo.getRankName());

                   if(genderChange || companyCodeChange || orgIdChange || orgTxtChange ||
                           positionIdChange || positionTextChange || costCenterChange ||
                           emailChange || firstNameChange || lastNameChange ||
                           chineseNameChange || workingLocationChange || rankNameChange){
                       log.info("------begin modify current user: {} ------", hrCwid);
                       log.info("changed flag: {}, hrCompanyCode: {}, currentCompanyCode: {}",companyCodeChange,hrCompanyCode, employeeInfo.getCompanyCode());
                       log.info("changed flag: {}, hrOrgId: {}, currentOrgId: {}",orgIdChange,hrOrgId, employeeInfo.getOrgId());
                       log.info("changed flag: {}, hrOrgTxt: {}, currentOrgTxt: {}",orgTxtChange,hrOrgTxt, employeeInfo.getOrgTxt());
                       log.info("changed flag: {}, hrPositionId: {}, currentPositionId: {}",positionIdChange,hrPositionId, employeeInfo.getPositionId());
                       log.info("changed flag: {}, hrPositionTxt: {}, currentPositionTxt: {}",positionTextChange,hrPositionTxt, employeeInfo.getPositionTxt());
                       log.info("changed flag: {}, hrCostCenter: {}, currentCostCenter: {}",costCenterChange,hrCostCenter, employeeInfo.getCostCenter());
                       log.info("changed flag: {}, hrEmailAddress: {}, currentEmailAddress: {}",emailChange,hrEmailAddress, employeeInfo.getEmail());
                       log.info("changed flag: {}, hrFirstName: {}, currentFirstName: {}",firstNameChange,hrFirstName, employeeInfo.getFirstName());
                       log.info("changed flag: {}, hrLastName: {}, currentLastName: {}",lastNameChange,hrLastName, employeeInfo.getLastName());
                       log.info("changed flag: {}, hrChineseName: {}, currentChineseName: {}",chineseNameChange,hrChineseName, employeeInfo.getChineseName());
                       log.info("changed flag: {}, hrWorkingLocation: {}, currentWorkingLocation: {}",workingLocationChange,hrWorkingLocation, employeeInfo.getLocation());
                       log.info("changed flag: {}, hrEmployeeGender: {}, currentEmployeeGender: {}",genderChange,hrEmployeeGender, employeeInfo.getGender());
                       log.info("------end modify ------");
                       //change
                       BeanUtils.copyProperties(hrCentralEmployee, syncEmployeeInfo);
                       syncEmployeeInfo.setSfUserId(hrCentralEmployee.getUserId());
                       syncEmployeeInfo.setChineseName(hrChineseName);
                       syncEmployeeInfo.setFirstName(hrFirstName);
                       syncEmployeeInfo.setLastName(hrLastName);
                       syncEmployeeInfo.setEmail(hrEmailAddress);
                       syncEmployeeInfo.setLocation(hrWorkingLocation);
                       syncEmployeeInfo.setRankName(rankName);
                       syncEmployeeInfo.setOrgId(hrOrgId);
                       syncEmployeeInfo.setMarkDeleted(hrEmployeeMarkDel);
                       syncEmployeeInfo.setDimissionDate(hrLeavingDate);
                       syncEmployeeInfo.setGender(hrEmployeeGender);
                       syncEmployeeInfo.setOperateStatus(Constant.CTRIP_OPREATE_MODIFY_STATUS);
                       modifyTotal = modifyTotal + 1;
                       syncEmployeeInfoList.add(syncEmployeeInfo);
                   }
                }
            }
        }
        dataMap.put("SyncModifySize",modifyTotal);
        dataMap.put("SyncDeleteSize",delTotal);

        return syncEmployeeInfoList;
    }

}
