package com.dne.ctrip.excel.model;

import java.util.Date;

 public  interface IBaseEmpInfoRowModel {

     String getCategory();

     void setCategory(String category);
     String getIpin();

     void setIpin(String ipin);
     String getCnName();
     
     void setCnName(String cnName);

     String getEnName();

     void setEnName(String enName);

     String getCwid() ;

     void setCwid(String cwid) ;

     String getGender() ;

     void setGender(String gender);

     String getType() ;

     void setType(String type) ;

     Date getAdjustedServiceDate() ;

     void setAdjustedServiceDate(Date adjustedServiceDate) ;

     Date getCompanyEntryDate() ;

     void setCompanyEntryDate(Date companyEntryDate);

     String getCompanyServiceYear() ;

     void setCompanyServiceYear(String companyServiceYear) ;

     String getAdjustedServiceYear();

     void setAdjustedServiceYear(String adjustedServiceYear) ;

     Date getContractorStartDay() ;

     void setContractorStartDay(Date contractorStartDay) ;

     String getOrg1();

     void setOrg1(String org1) ;

     String getOrg2() ;

     void setOrg2(String org2) ;

     String getOrg3() ;

     void setOrg3(String org3) ;

     String getOrg4() ;

     void setOrg4(String org4);

     String getOrg5();

     void setOrg5(String org5);

     String getOrg6();

     void setOrg6(String org6);

     String getOrg7() ;

     void setOrg7(String org7);

     String getOrg8() ;

     void setOrg8(String org8);

     String getOrg9();

     void setOrg9(String org9) ;

     String getPositionId();

     void setPositionId(String positionId);

     String getPositionTxt();

     void setPositionTxt(String positionTxt) ;
     String getOrgTxt() ;

     void setOrgTxt(String orgTxt);

     String getChineseTitle() ;

     void setChineseTitle(String chineseTitle);

     String getAbbrvOfPosition();

     void setAbbrvOfPosition(String abbrvOfPosition);

     String getOrgId();

     void setOrgId(String orgId);

     String getCostCenter() ;

     void setCostCenter(String costCenter);

     String getCostCenterName();

     void setCostCenterName(String costCenterName);

     String getResourceManagerEnName();

     void setResourceManagerEnName(String resourceManagerEnName);

     String getResourceManagerIpin();
     void setResourceManagerIpin(String resourceManagerIpin);

     String getResourceManagerCwid();

     void setResourceManagerCwid(String resourceManagerCwid);

     String getAdminManagerEnName() ;

     void setAdminManagerEnName(String adminManagerEnName);

     String getAdminManagerIpin() ;

     void setAdminManagerIpin(String adminManagerIpin);

     String getAdminManagerCwid() ;

     void setAdminManagerCwid(String adminManagerCwid) ;

     String getProvince() ;

     void setProvince(String province);

     String getCity() ;

     void setCity(String city) ;

     String getBusinessAddress();

     void setBusinessAddress(String businessAddress);

     String getEmail();

     void setEmail(String email) ;
}
