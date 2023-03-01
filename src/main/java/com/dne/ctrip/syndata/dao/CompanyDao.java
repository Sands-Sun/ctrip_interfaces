package com.dne.ctrip.syndata.dao;

import com.dne.ctrip.entity.CompanyCodeAndCostCenterRef;
import com.dne.ctrip.entity.CompanyNameManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyDao {

     List<CompanyNameManagement> getCompanyNames();
     List<CompanyCodeAndCostCenterRef> getCompanyCodeByOrderIds(@Param("items") List<String> orderIds);
     List<CompanyCodeAndCostCenterRef> getCompanyCodeByCwIds(@Param("items") List<String> cwIds);
     CompanyCodeAndCostCenterRef getCompanyCodeByIpin(String ipin);
     List<CompanyCodeAndCostCenterRef> getCompanyCodeByCenterCodes(@Param("items") List<String> costCenters);
}
