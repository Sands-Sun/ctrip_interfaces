package com.dne.ctrip.syndata.service;

import com.dne.ctrip.entity.EmployeeInfo;
import com.dne.ctrip.entity.EmployeeRankName;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.ctrip.syndata.dao.EmployeeDao;
import com.dne.hrcentral.dao.HrCentralDao;
import com.dne.hrcentral.entity.HRCentralEmployee;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EmployeeService {


    private Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private HrCentralDao hrCentralDao;

    @Value("${jdbc.hrcentraldb.synchaccount}")
    private String synAccount;


    public List<HRCentralEmployee> getHREmployeeInfoFromHRCentral() {
        return hrCentralDao.getEmployeeInfoList(synAccount);
    }

    public int getHREmployeeInfoCount() {
        return employeeDao.getHREmployeeInfoCount();
    }

    public void deleteAllHREmployeeInfo() {
        employeeDao.deleteAllHREmployeeInfo();
    }


    public void syncPrevHREmployeeInfo() {
        employeeDao.deleteAllPrevHREmployeeInfo();
        employeeDao.savePrevHREmployeeInfo();
    }


    public void batchSaveHREmployeeInfo(List<HRCentralEmployee> employees, int batchCount){
        List<List<HRCentralEmployee>> partitionEmployees =
                Lists.partition(employees, batchCount);
        log.info("Partition HR central employee size: {}", partitionEmployees.size());
        for(int i = 0; i < partitionEmployees.size(); i++ ){
            List<HRCentralEmployee> subEmployeeList = partitionEmployees.get(i);
            log.debug("Save partition of HREmployee index: {}, subList count: {} ", i,subEmployeeList.size());
            employeeDao.batchSaveHREmployeeInfo(subEmployeeList);
        }
    }

    public void batchSaveSyncEmployeeInfo(List<SyncEmployeeInfo> employees, int batchCount) {
        List<List<SyncEmployeeInfo>> partitionEmployees =
                Lists.partition(employees, batchCount);
        log.info("Partition employee size: {}", partitionEmployees.size());
        for(int i = 0; i < partitionEmployees.size(); i++){
            List<SyncEmployeeInfo> subEmployeeList = partitionEmployees.get(i);
            log.info("Save partition of syncEmployeeInfo index: {}, subList count: {} ", i,subEmployeeList.size());
            employeeDao.batchSaveSyncEmployeeInfo(subEmployeeList);
        }
    }

    public void batchSaveEmployeeInfo(List<EmployeeInfo> employees, int batchCount) {
        List<List<EmployeeInfo>> partitionEmployees =
                Lists.partition(employees, batchCount);
        log.info("Partition employee information size: {}", partitionEmployees.size());
        for(int i = 0; i < partitionEmployees.size(); i++ ){
            List<EmployeeInfo> subEmployeeList = partitionEmployees.get(i);
            log.debug("Save partition of Employee information index: {}, subList count: {} ", i,subEmployeeList.size());
            employeeDao.batchSaveEmployeeInfo(subEmployeeList);
        }
    }

    public void syncEmployeeInfo(String batchNo){
         employeeDao.syncEmployeeInfo(batchNo);
    }

    public void batchUpdateSyncEmployeeStatus(String batchNo,String syncStatus,List<String> cwids, int batchQueryCount) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("batchNo",batchNo);
        paramMap.put("syncStatus",syncStatus);
        List<List<String>> partitions = Lists.partition(cwids,batchQueryCount);
        for(List<String> subCwids : partitions){
            paramMap.put("items", subCwids);
            employeeDao.updateSyncEmployeeStatus(paramMap);
        }
    }

    public List<EmployeeInfo> getPrevHREmployeeInfoByBatchNo(String batchNo) {
        return employeeDao.getPrevHREmployeeInfoByBatchNo(batchNo);
    }


    public List<SyncEmployeeInfo> getSyncEmployeeInfoByBatchNo(String batchNo) {
        return employeeDao.getSyncEmployeeInfoByBatchNo(batchNo);
    }
    public int getEmployeeInfoCount() {
        return employeeDao.getEmployeeInfoCount();
    }

    public List<EmployeeInfo> getEmployeeInfoByType(String type) {
        return employeeDao.getEmployeeInfoByType(type);
    }

    public List<EmployeeRankName> getEmployeeRankNameByType(String type) {
        return employeeDao.getEmployeeRankNameByType(type);
    }

    public void syncPrevRankName(){
        employeeDao.deleteAllPrevEmployeeRankName();
        employeeDao.savePrevEmployeeRankName();
    }

    public List<HRCentralEmployee> getHREmployeeInfoByType(String type) {
        return employeeDao.getHREmployeeInfoByType(type);
    }
    public void saveOrUpdateEmployeeInfo(String batchNo) {
        employeeDao.saveOrUpdateEmployeeInfo(batchNo);
    }
}
