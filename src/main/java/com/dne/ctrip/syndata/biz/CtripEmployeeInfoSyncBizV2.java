package com.dne.ctrip.syndata.biz;

import com.dne.core.common.Constant;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.entity.EmployeeInfo;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.ctrip.mail.vo.EmployeeSyncJobMailVo;
import com.dne.hrcentral.entity.HRCentralEmployee;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dne.core.common.Constant.CTRIP_JOB_FAIL_STATUS;
import static com.dne.core.common.Constant.CTRIP_OPREATE_MODIFY_STATUS;
import static com.dne.core.common.Constant.CTRIP_SYNC_STATUS_N;

@Component
public class CtripEmployeeInfoSyncBizV2 extends CtripEmployeeInfoSyncBiz{


    @Override
    public void processData(Map<String, Object> dataMap, EmployeeSyncJobMailVo mailVo) {
        log.info("file.encoding=" + System.getProperty("file.encoding"));
        //同步员工信息到携程API错误
        if(Objects.nonNull(jobStatus)
                && jobStatus.getRunStatus().equals(CTRIP_JOB_FAIL_STATUS)
                && jobStatus.getErrorCode() == 1000) {
            String pervBatchNo = jobStatus.getBatchId();
            log.info("Previous Employee information batchNo: " + pervBatchNo);
            log.info("Previous job run error...");
            log.info("Previous job batch no: {}",pervBatchNo);
            List<SyncEmployeeInfo> syncEmployeeInfoList = employeeService.getSyncEmployeeInfoByBatchNo(pervBatchNo);
            List<SyncEmployeeInfo> errorSyncList = syncEmployeeInfoList.stream().filter(
                    e -> e.getSyncStatus().equals(CTRIP_SYNC_STATUS_N)).collect(Collectors.toList());
            mailVo.setTotalSync(errorSyncList.size());
            if(CollectionUtils.isNotEmpty(errorSyncList)){
                postEmployeeIntoToCtrip(pervBatchNo,errorSyncList);
            }
        } else {
//            syncEmployeeInfoFromHRCentral(mailVo);
//            //初始化员工信息
//            initEmployeeInfoFromExcelFile();
//            List<SyncEmployeeInfo> saveSyncEmployeeInfoList = Lists.newArrayList();
//            //获取新增员工记录
//            convertNewCreateToSyncEmployee();
//            if(CollectionUtils.isNotEmpty(newCreateEmployeeList)){
//                int createSize = newCreateEmployeeList.size();
//                log.info("New create employee size: {}", createSize);
//                employeeService.batchSaveSyncEmployeeInfo(newCreateEmployeeList, batchCount);
//                saveSyncEmployeeInfoList.addAll(newCreateEmployeeList);
//                mailVo.setTotalSyncNewCreate(createSize);
//            }
//            //获取员工更改信息记录 && 离职员工信息记录
//            convertChangedToSyncEmployee();
//            if(CollectionUtils.isNotEmpty(modifiedEmployeeList)){
//                int modifiedSize = modifiedEmployeeList.size();
//                log.info("Modify employee size: {}", modifiedSize);
//                employeeService.batchSaveSyncEmployeeInfo(modifiedEmployeeList, batchCount);
//                saveSyncEmployeeInfoList.addAll(modifiedEmployeeList);
//                mailVo.setTotalSyncModified(modifiedSize);
//            }
//            if(CollectionUtils.isNotEmpty(deletedEmployeeList)){
//                int deletedSize = deletedEmployeeList.size();
//                log.info("Delete employee size: {}", deletedSize);
//                employeeService.batchSaveSyncEmployeeInfo(deletedEmployeeList, batchCount);
//                saveSyncEmployeeInfoList.addAll(deletedEmployeeList);
//                mailVo.setTotalSyncDeleted(deletedSize);
//            }
//
//            mailVo.setTotalSync(saveSyncEmployeeInfoList.size());
//            postEmployeeIntoToCtrip(batchNo,saveSyncEmployeeInfoList);
//            employeeService.saveOrUpdateEmployeeInfo(batchNo);
        }
//        exportEmployeeInfoChangedToFile(mailVo);
    }

    private void convertNewCreateToSyncEmployee() {
        List<EmployeeInfo> employeeInfoList = employeeService.getEmployeeInfoByType("create");
        SyncEmployeeInfo syncEmployeeInfo;
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
            newCreateEmployeeList.add(syncEmployeeInfo);
        }
    }

    private void convertChangedToSyncEmployee() {
        List<EmployeeInfo> employeeInfoList = employeeService.getEmployeeInfoByType("change");
        if(CollectionUtils.isEmpty(employeeInfoList)){
            return;
        }
        compareChangeEmployees(employeeInfoList);
    }


    private void compareChangeEmployees(List<EmployeeInfo> changeEmployees) {
        SyncEmployeeInfo syncEmployeeInfo;
        for(HRCentralEmployee hrCentralEmployee : currentHREmployeeInfoList){
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
                         log.info("-----HrCentral not Chinese name: {}" , hrChineseName);
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
                        log.info("Delete employee cwid ****: {}", syncEmployeeInfo.getCwid());
                        deletedEmployeeList.add(syncEmployeeInfo);
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
                       syncEmployeeInfo.setOperateStatus(CTRIP_OPREATE_MODIFY_STATUS);
                       modifiedEmployeeList.add(syncEmployeeInfo);
                   }
                }
            }
        }
    }
}
