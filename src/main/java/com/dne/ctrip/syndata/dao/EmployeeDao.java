package com.dne.ctrip.syndata.dao;


import com.dne.ctrip.entity.EmployeeInfo;
import com.dne.ctrip.entity.EmployeeRankName;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.hrcentral.entity.HRCentralEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

    void deleteSyncEmployeeInfo(String batchNo);
    void savePrevHREmployeeInfo();
    int getHREmployeeInfoCount();
    void deleteAllHREmployeeInfo();

    void deleteAllPrevHREmployeeInfo();
    void batchSaveHREmployeeInfo(@Param("employees") List<HRCentralEmployee> employees);
    void batchSaveSyncEmployeeInfo(@Param("employees") List<SyncEmployeeInfo> employees);
    void batchSaveEmployeeInfo(@Param("employees") List<EmployeeInfo> employees);

    void updateSyncEmployeeStatus(Map<String, Object> paramMap);
    void syncEmployeeInfo(String batchNo);
    int getEmployeeInfoCount();

    List<EmployeeRankName> getEmployeeRankNameByType(@Param("type") String type);

    void savePrevEmployeeRankName();

    void deleteAllPrevEmployeeRankName();

    List<EmployeeInfo> getEmployeeInfoByType(@Param("type") String type);
    List<SyncEmployeeInfo> getSyncEmployeeInfoByBatchNo(String batchNo);

    void saveOrUpdateEmployeeInfo(String batchNo);

    List<HRCentralEmployee> getHREmployeeInfoByType(@Param("type") String type);
}
