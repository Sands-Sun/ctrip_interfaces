package com.dne.ctrip.syndata.biz;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.dne.core.basic.entity.JobStatusDetail;
import com.dne.core.common.BaseException;
import com.dne.core.common.Constant;
import com.dne.core.common.CtripAbsRiverBiz;
import com.dne.core.util.DateUtils;
import com.dne.core.util.EasyExcelUtil;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.domain.ErrorMessage;
import com.dne.ctrip.domain.ResponseStatusList;
import com.dne.ctrip.entity.*;
import com.dne.ctrip.excel.model.ActiveEmpInfoRowModel;
import com.dne.ctrip.excel.model.IBaseEmpInfoRowModel;
import com.dne.ctrip.excel.model.SeparationEmpInfoRowModel;
import com.dne.ctrip.param.CorpAuthEmployee;
import com.dne.ctrip.param.CorpAuthEmployeeInfo;
import com.dne.ctrip.param.CorpCostCenter;
import com.dne.ctrip.param.CorpCustomInfoInfo;
import com.dne.ctrip.syndata.dao.CompanyDao;
import com.dne.ctrip.syndata.service.EmployeeService;
import com.dne.hrcentral.entity.HRCentralEmployee;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.dne.core.common.ErrorEnum.*;


@Component
public abstract class CtripEmployeeInfoSyncBiz extends CtripAbsRiverBiz {

    @Autowired
    protected EmployeeService employeeService;

    @Autowired
    protected CompanyDao companyDao;

    @Value("${ctrip.sub.account.name.prefix}")
    private String subAccountNamePrefix;


    @Value("${employee.info.file}")
    private String employeeInfoFile;

    @Value("${sync.debug}")
    private boolean isDebug;

    protected static Map<String, String> SUB_ACCOUNT_NAME_MAP = new HashMap<>();

    protected List<HRCentralEmployee> currentHREmployeeInfoList;

    protected List<String> errorSyncToCtripCwid = new ArrayList<>();
    protected Map<String, String> rankNameMap;

    public CtripEmployeeInfoSyncBiz() {
        super(Constant.CTRIP_SYNC_EMPLOYEE_JOB_NAME);
    }

    @Override
    protected void hookInit () {
        initSubAccountName();
        initRankNames();
    }

    private void initSubAccountName() {
        List<CompanyNameManagement> companyNames = companyDao.getCompanyNames();
        if(CollectionUtils.isNotEmpty(companyNames)){
           for(CompanyNameManagement companyName : companyNames){
              if("0813".equals(companyName.getCompanyCode())){
                  //总公司
                  SUB_ACCOUNT_NAME_MAP.put("0813", subAccountNamePrefix);
              }else {
                  SUB_ACCOUNT_NAME_MAP.put(companyName.getCompanyCode(),
                          subAccountNamePrefix + "_" + companyName.getCompanyNameCn());
              }
           }
        }
    }

    private void initRankNames() {
        List<EmployeeRankName>  rankNames = employeeService.getEmployeeRankNameByType("current");
        log.info("Employee rankName size: {}", rankNames.size());
        rankNameMap = rankNames.stream().collect(
                Collectors.toMap(EmployeeRankName::getCwid, EmployeeRankName::getRankName,
                        (oldValue, newValue) -> newValue));
        employeeService.syncPrevRankName();
        List<EmployeeRankName>  prevRankNames = employeeService.getEmployeeRankNameByType("prev");
        log.info("Employee prevRankName size: {}", prevRankNames.size());
    }



    protected void initEmployeeInfoFromExcelFile() {
        int totalEmployeeCount = employeeService.getEmployeeInfoCount();
        log.info("Employee information count: {}", totalEmployeeCount);
        if(totalEmployeeCount > 0) {
            return;
        }
        log.info("First run load employee information from excel file...");
        try (InputStream spearInputStream = Files.newInputStream(Paths.get(employeeInfoFile));
             InputStream activeInputStream = Files.newInputStream(Paths.get(employeeInfoFile))) {
            EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
            log.info("------------start process load employee info -----");
            Pair<List<SeparationEmpInfoRowModel>, Integer> resultSeparationMap =
                    easyExcelUtil.readExcel2007(spearInputStream,2,1, ExcelTypeEnum.XLSX, SeparationEmpInfoRowModel.class);
            List<SeparationEmpInfoRowModel> separations = resultSeparationMap.getKey();
            log.info("SeparationEmpInfoRowModel.size:{}---------------totalExcelRow:{}---------------",
                    separations.size(),resultSeparationMap.getValue());
            saveEmployee(separations);
            Pair<List<ActiveEmpInfoRowModel>, Integer> resultActiveMap =
                    easyExcelUtil.readExcel2007(activeInputStream, 1,1,ExcelTypeEnum.XLSX, ActiveEmpInfoRowModel.class);
            List<ActiveEmpInfoRowModel> actives = resultActiveMap.getKey();
            log.info("ActiveEmpInfoRowModel.size:{}---------------totalExcelRow:{}---------------",
                    actives.size(),resultActiveMap.getValue());
            saveEmployee(actives);
        }catch (IOException e) {
            log.error("IOException: ",e);
            throw new BaseException(EMP_READ_FILE_ERROR.getCode(),
                    EMP_READ_FILE_ERROR.getMessage(),e);
        }
    }


    private void saveEmployee(List<? extends IBaseEmpInfoRowModel> rowModels) {
        List<EmployeeInfo> employeeInfoList = convertRowModelToEmployee(rowModels);
        log.info("After convert save to employee size: {}", employeeInfoList.size());
        employeeService.batchSaveEmployeeInfo(employeeInfoList,batchCount);
    }

    private List<EmployeeInfo> convertRowModelToEmployee(List<? extends IBaseEmpInfoRowModel> rowModels) {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>(rowModels.size());
        EmployeeInfo employeeInfo;
        Map<String, HRCentralEmployee> hrCentralEmployeeMap = currentHREmployeeInfoList.stream().collect(
                Collectors.toMap(HRCentralEmployee::getCwid, c-> c, (oldValue, newValue) -> newValue));
        for(IBaseEmpInfoRowModel rowModel : rowModels){
            String cwid = rowModel.getCwid();
            if(hrCentralEmployeeMap.containsKey(cwid)){
                HRCentralEmployee hrCentralEmployee = hrCentralEmployeeMap.get(cwid);
                String markDeleted = "N";
                String location = hrCentralEmployee.getWorkingLocation();
                Date dimissionDate = hrCentralEmployee.getLeavingDate();
                Integer sfUserId = hrCentralEmployee.getUserId();
                if(StringUtils.isEmpty(rowModel.getEmail())){
                    log.info("---Empty employee email address: {} ---", cwid);
                    rowModel.setEmail(hrCentralEmployee.getEmail());
                }
                if(rowModel instanceof SeparationEmpInfoRowModel){
                    SeparationEmpInfoRowModel separation = (SeparationEmpInfoRowModel) rowModel;
                    markDeleted = "Organizational Change".equals(separation.getActionType()) ? "N" : "Y";
                    location = separation.getLocation();
                    dimissionDate = separation.getLastWorkingDay();
                }
                employeeInfo = new EmployeeInfo();
                BeanUtils.copyProperties(rowModel, employeeInfo);
                copyRowModelToEmployee(employeeInfo,rowModel,sfUserId,markDeleted,location,cwid,dimissionDate);
                employeeInfoList.add(employeeInfo);
            }
        }
        return employeeInfoList;
    }


    private void copyRowModelToEmployee(EmployeeInfo employeeInfo,IBaseEmpInfoRowModel rowModel,
                                        Integer sfUserId, String markDeleted,String location,
                                        String cwid,Date dimissionDate) {
        String enName = rowModel.getEnName();
        String org1 = rowModel.getOrg1();
        String ipin = rowModel.getIpin();
        BeanUtils.copyProperties(rowModel, employeeInfo);
        String rankName = rankNameMap.getOrDefault(cwid, "Staffs");
        employeeInfo.setIpin(String.format("%08d", Integer.valueOf(ipin)));
        employeeInfo.setSfUserId(sfUserId);
        employeeInfo.setRankName(rankName);
        employeeInfo.setCompanyCode(org1.substring(0,4));
        employeeInfo.setFirstName(enName.substring(0,enName.lastIndexOf(" ")));
        employeeInfo.setLastName(enName.substring(enName.lastIndexOf( " ") + 1));
        employeeInfo.setChineseName(rowModel.getCnName());
        employeeInfo.setLocation(location);
        employeeInfo.setMarkDeleted(markDeleted);
        employeeInfo.setDimissionDate(dimissionDate);
    }


    protected void postEmployeeIntoToCtrip(String batchNo,List<SyncEmployeeInfo> infoList) {
        if(isDebug){
            log.debug("Debug do not update user information to ctrip.");
            return;
        }
        CorpCustomInfoInfo corpCustomInfoInfo = convertToCorpEmployee(infoList);
        if(CollectionUtils.isNotEmpty(corpCustomInfoInfo.getCorpAuthEmployeeInfoList())){
            List<String> cwids = infoList.stream().map(EmployeeInfo::getCwid).distinct().collect(Collectors.toList());
            ResponseStatusList responseStatus =  handler.batchSaveEmployeeInfo(corpCustomInfoInfo);
           if(handelErrorPostEmployeeInfo(responseStatus, corpCustomInfoInfo)){
               if(CollectionUtils.isNotEmpty(errorSyncToCtripCwid)){
                    log.info("Error sync to ctrip cwid size: {}", errorSyncToCtripCwid.size());
                    employeeService.batchUpdateSyncEmployeeStatus(
                            batchNo,Constant.CTRIP_SYNC_STATUS_N, errorSyncToCtripCwid,batchQueryCount);
               }
               throw  new BaseException(EMP_SYNC_API_ERROR.getCode(), EMP_SYNC_API_ERROR.getMessage());
           }
           updateSuccessSyncStatus(batchNo,cwids);
        }
    }

    private void updateSuccessSyncStatus(String batchNo,List<String> cwids) {
        cwids.removeAll(errorSyncToCtripCwid);
        log.info("Success Sync to Ctrip size:{}", cwids.size());
        employeeService.batchUpdateSyncEmployeeStatus(batchNo, Constant.CTRIP_SYNC_STATUS_Y,cwids,batchQueryCount);
    }
    private boolean handelErrorPostEmployeeInfo(ResponseStatusList responseStatus, CorpCustomInfoInfo corpCustomInfoInfo) {
        boolean errorFlag = false;
        //接口调用结果Success: 更新成功；Failed：更新失败；Contains errors：部分数据未更新成功；
        String respStatus = responseStatus.getStatus();
        if(!"Success".equals(respStatus)){
            errorFlag = true;
            List<JobStatusDetail> jobStatusDetailList = new ArrayList<>(responseStatus.getMessages().size());
            List<CorpAuthEmployeeInfo> corpAuthEmployees = corpCustomInfoInfo.getCorpAuthEmployeeInfoList();
            for(ErrorMessage errorMessage : responseStatus.getMessages()){
                Integer sequence = errorMessage.getSequence();
                for(CorpAuthEmployeeInfo info : corpAuthEmployees){
                    if(sequence.equals(Integer.parseInt(info.getSequence()))){
                        CorpAuthEmployee errorCorpAuthEmployee =  info.getCorpAuthEmployee();
                        if(errorCorpAuthEmployee != null){
                            String cwid = errorCorpAuthEmployee.getCwid();
                            errorSyncToCtripCwid.add(cwid);
                            String errorDetail = JSONObject.toJSONString(errorMessage);
                            log.debug("Error Sync to Ctrip, cwid: {}, error: {}", cwid,errorDetail);
                            JobStatusDetail jobStatusDetail = new JobStatusDetail(jobName,batchNo, cwid, errorDetail);
                            jobStatusDetailList.add(jobStatusDetail);
                        }
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(jobStatusDetailList)){
                log.debug("Save error information to status detail...");
                jobStatusDao.batchSaveStatusDetail(jobStatusDetailList);
            }
        }
        return errorFlag;
    }

    protected CorpCustomInfoInfo convertToCorpEmployee(List<SyncEmployeeInfo> saveSyncEmployeeInfoList) {
        CorpCustomInfoInfo corpCustomInfoInfo = new CorpCustomInfoInfo();
        List<CorpAuthEmployeeInfo> corpAuthEmployeeList = new ArrayList<>(saveSyncEmployeeInfoList.size());
        for(int i=0; i< saveSyncEmployeeInfoList.size(); i++){
            SyncEmployeeInfo syncEmployeeInfo = saveSyncEmployeeInfoList.get(i);
            CorpAuthEmployeeInfo corpAuthEmployeeInfo = new CorpAuthEmployeeInfo();
            CorpAuthEmployee corpAuthEmployee = new CorpAuthEmployee();
            CorpCostCenter corpCostCenter = new CorpCostCenter();
            corpAuthEmployeeInfo.setSequence(String.valueOf(i));
            BeanUtils.copyProperties(syncEmployeeInfo, corpAuthEmployee);
            corpCostCenter.setContent(syncEmployeeInfo.getCostCenter());
            corpAuthEmployee.setCorpCostCenters(Collections.singletonList(corpCostCenter));
            // A-在职, I-离职
            corpAuthEmployee.setValid("N".equals(corpAuthEmployee.getMarkDeleted()) ? "A" : "I");
            //性别（M:男,F:女）
            corpAuthEmployee.setGender("Male".equals(corpAuthEmployee.getGender()) ? "M" : "F");
            //Concur账户 默认为用户邮箱
            if(StringUtils.isNotEmpty(syncEmployeeInfo.getEmail())){
             corpAuthEmployee.setConcurAccount(syncEmployeeInfo.getEmail());
            }
            String companyCode = corpAuthEmployee.getCompanyCode();
            if(SUB_ACCOUNT_NAME_MAP.containsKey(companyCode)){
                String subAccountName = SUB_ACCOUNT_NAME_MAP.get(companyCode);
                log.debug("company code: {} ,sub account name value: {}", companyCode, subAccountName);
                corpAuthEmployee.setSubAccountName(subAccountName);
            }
            corpAuthEmployeeInfo.setCorpAuthEmployee(corpAuthEmployee);
            corpAuthEmployeeList.add(corpAuthEmployeeInfo);
        }
        corpCustomInfoInfo.setCorpAuthEmployeeInfoList(corpAuthEmployeeList);
        return corpCustomInfoInfo;
    }


    protected void syncEmployeeInfoFromHRCentral(Map<String, Object> dataMap) {
        int beforeTotal = employeeService.getHREmployeeInfoCount();
        log.info("Begin sync HR Employee information batchNo: {}, before totalCount:{}", batchNo, beforeTotal);
        currentHREmployeeInfoList = employeeService.getHREmployeeInfoFromHRCentral();
        log.info("Current HR central employee size: {}", currentHREmployeeInfoList.size());
        employeeService.syncPrevHREmployeeInfo();
        employeeService.deleteAllHREmployeeInfo();
        employeeService.batchSaveHREmployeeInfo(currentHREmployeeInfoList,batchCount);
        int afterTotal = employeeService.getHREmployeeInfoCount();
        log.info("End sync HR Employee information after totalCount:{}", afterTotal);
        dataMap.put("beforeSyncHREmployeeCount", beforeTotal);
        dataMap.put("afterSyncHREmployeeCount", afterTotal);
    }

}
