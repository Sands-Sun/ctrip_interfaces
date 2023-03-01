package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ActiveEmpInfoRowModel  extends BaseRowModel implements IBaseEmpInfoRowModel{

    @ExcelProperty(value = "Employee Category", index = 0)
    private String category;

    @ExcelProperty(value = "IPIN", index = 1)
    private String ipin;

    @ExcelProperty(value = "Employee Name (CN)", index = 2)
    private String cnName;

    @ExcelProperty(value = "Employee Name (EN)", index = 3)
    private String enName;

    @ExcelProperty(value = "CWID", index = 4)
    private String cwid;

    @ExcelProperty(value = "Gender", index = 5)
    private String gender;

    @ExcelProperty(value = "Employee Type", index = 6)
    private String type;

    @ExcelProperty(value = "Adjusted ServiceDate", index = 7)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date adjustedServiceDate;

    @ExcelProperty(value = "Adjusted Service Year", index = 8)
    private String adjustedServiceYear;

    @ExcelProperty(value = "Company Entry Date", index = 9)
    private Date companyEntryDate;

    @ExcelProperty(value = "Company Service Year", index = 10)
    private String companyServiceYear;

    @ExcelProperty(value = "Contractor start day", index = 11)
    private Date contractorStartDay;

    @ExcelProperty(value = "ORG1", index = 12)
    private String org1; //company_code + company_name

    @ExcelProperty(value = "ORG2", index = 13)
    private String org2;

    @ExcelProperty(value = "ORG3", index = 14)
    private String org3;

    @ExcelProperty(value = "ORG4", index = 15)
    private String org4;

    @ExcelProperty(value = "ORG5", index = 16)
    private String org5;

    @ExcelProperty(value = "ORG6", index = 17)
    private String org6;

    @ExcelProperty(value = "ORG7", index = 18)
    private String org7;

    @ExcelProperty(value = "ORG8", index = 19)
    private String org8;

    @ExcelProperty(value = "ORG9", index = 20)
    private String org9;

    @ExcelProperty(value = "Position ID", index = 21)
    private String positionId;

    @ExcelProperty(value = "Position Name", index = 22)
    private String positionTxt;

    @ExcelProperty(value = "Chinese Title", index = 23)
    private String chineseTitle;

    @ExcelProperty(value = "Abbrv. of position", index = 24)
    private String abbrvOfPosition;

    @ExcelProperty(value = "Org.Unit Code", index = 25)
    private String orgId;

    @ExcelProperty(value = "Org.Unit", index = 26)
    private String orgTxt;

    @ExcelProperty(value = "Cost Center", index = 27)
    private String costCenter;

    @ExcelProperty(value = "Cost Center Name", index = 28)
    private String costCenterName;

    @ExcelProperty(value = "Resource Manager (EN)", index = 29)
    private String  resourceManagerEnName;

    @ExcelProperty(value = "Resource Manager IPIN", index = 30)
    private String  resourceManagerIpin;

    @ExcelProperty(value = "Resource manager CWID", index = 31)
    private String  resourceManagerCwid;

    @ExcelProperty(value = "Admin Manager (EN)", index = 32)
    private String  adminManagerEnName;

    @ExcelProperty(value = "Admin Manager IPIN", index = 33)
    private String  adminManagerIpin;

    @ExcelProperty(value = "Admin Manager CWID", index = 34)
    private String  adminManagerCwid;

    @ExcelProperty(value = "Chinese Province", index = 35)
    private String province;

    @ExcelProperty(value = "Chinese City", index = 36)
    private String city;

    @ExcelProperty(value = "Business Address_ZH", index = 37)
    private String businessAddress;

    @ExcelProperty(value = "Email Address", index = 38)
    private String email;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIpin() {
        return ipin;
    }

    public void setIpin(String ipin) {
        this.ipin = ipin;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAdjustedServiceDate() {
        return adjustedServiceDate;
    }

    public void setAdjustedServiceDate(Date adjustedServiceDate) {
        this.adjustedServiceDate = adjustedServiceDate;
    }

    public Date getCompanyEntryDate() {
        return companyEntryDate;
    }

    public void setCompanyEntryDate(Date companyEntryDate) {
        this.companyEntryDate = companyEntryDate;
    }

    public String getCompanyServiceYear() {
        return companyServiceYear;
    }

    public void setCompanyServiceYear(String companyServiceYear) {
        this.companyServiceYear = companyServiceYear;
    }

    public String getAdjustedServiceYear() {
        return adjustedServiceYear;
    }

    public void setAdjustedServiceYear(String adjustedServiceYear) {
        this.adjustedServiceYear = adjustedServiceYear;
    }

    public Date getContractorStartDay() {
        return contractorStartDay;
    }

    public void setContractorStartDay(Date contractorStartDay) {
        this.contractorStartDay = contractorStartDay;
    }

    public String getOrg1() {
        return org1;
    }

    public void setOrg1(String org1) {
        this.org1 = org1;
    }

    public String getOrg2() {
        return org2;
    }

    public void setOrg2(String org2) {
        this.org2 = org2;
    }

    public String getOrg3() {
        return org3;
    }

    public void setOrg3(String org3) {
        this.org3 = org3;
    }

    public String getOrg4() {
        return org4;
    }

    public void setOrg4(String org4) {
        this.org4 = org4;
    }

    public String getOrg5() {
        return org5;
    }

    public void setOrg5(String org5) {
        this.org5 = org5;
    }

    public String getOrg6() {
        return org6;
    }

    public void setOrg6(String org6) {
        this.org6 = org6;
    }

    public String getOrg7() {
        return org7;
    }

    public void setOrg7(String org7) {
        this.org7 = org7;
    }

    public String getOrg8() {
        return org8;
    }

    public void setOrg8(String org8) {
        this.org8 = org8;
    }

    public String getOrg9() {
        return org9;
    }

    public void setOrg9(String org9) {
        this.org9 = org9;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionTxt() {
        return positionTxt;
    }

    public void setPositionTxt(String positionTxt) {
        this.positionTxt = positionTxt;
    }

    public String getOrgTxt() {
        return orgTxt;
    }

    public void setOrgTxt(String orgTxt) {
        this.orgTxt = orgTxt;
    }

    public String getChineseTitle() {
        return chineseTitle;
    }

    public void setChineseTitle(String chineseTitle) {
        this.chineseTitle = chineseTitle;
    }

    public String getAbbrvOfPosition() {
        return abbrvOfPosition;
    }

    public void setAbbrvOfPosition(String abbrvOfPosition) {
        this.abbrvOfPosition = abbrvOfPosition;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getResourceManagerEnName() {
        return resourceManagerEnName;
    }

    public void setResourceManagerEnName(String resourceManagerEnName) {
        this.resourceManagerEnName = resourceManagerEnName;
    }

    public String getResourceManagerIpin() {
        return resourceManagerIpin;
    }

    public void setResourceManagerIpin(String resourceManagerIpin) {
        this.resourceManagerIpin = resourceManagerIpin;
    }

    public String getResourceManagerCwid() {
        return resourceManagerCwid;
    }

    public void setResourceManagerCwid(String resourceManagerCwid) {
        this.resourceManagerCwid = resourceManagerCwid;
    }

    public String getAdminManagerEnName() {
        return adminManagerEnName;
    }

    public void setAdminManagerEnName(String adminManagerEnName) {
        this.adminManagerEnName = adminManagerEnName;
    }

    public String getAdminManagerIpin() {
        return adminManagerIpin;
    }

    public void setAdminManagerIpin(String adminManagerIpin) {
        this.adminManagerIpin = adminManagerIpin;
    }

    public String getAdminManagerCwid() {
        return adminManagerCwid;
    }

    public void setAdminManagerCwid(String adminManagerCwid) {
        this.adminManagerCwid = adminManagerCwid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ActiveEmpInfoRowModel{" +
                "category='" + category + '\'' +
                ", ipin='" + ipin + '\'' +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", cwid='" + cwid + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", adjustedServiceDate=" + adjustedServiceDate +
                ", adjustedServiceYear='" + adjustedServiceYear + '\'' +
                ", companyEntryDate=" + companyEntryDate +
                ", companyServiceYear='" + companyServiceYear + '\'' +
                ", contractorStartDay=" + contractorStartDay +
                ", org1='" + org1 + '\'' +
                ", org2='" + org2 + '\'' +
                ", org3='" + org3 + '\'' +
                ", org4='" + org4 + '\'' +
                ", org5='" + org5 + '\'' +
                ", org6='" + org6 + '\'' +
                ", org7='" + org7 + '\'' +
                ", org8='" + org8 + '\'' +
                ", org9='" + org9 + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionTxt='" + positionTxt + '\'' +
                ", chineseTitle='" + chineseTitle + '\'' +
                ", abbrvOfPosition='" + abbrvOfPosition + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgTxt='" + orgTxt + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", costCenterName='" + costCenterName + '\'' +
                ", resourceManagerEnName='" + resourceManagerEnName + '\'' +
                ", resourceManagerIpin='" + resourceManagerIpin + '\'' +
                ", resourceManagerCwid='" + resourceManagerCwid + '\'' +
                ", adminManagerEnName='" + adminManagerEnName + '\'' +
                ", adminManagerIpin='" + adminManagerIpin + '\'' +
                ", adminManagerCwid='" + adminManagerCwid + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
