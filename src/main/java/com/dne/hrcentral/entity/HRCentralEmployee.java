package com.dne.hrcentral.entity;


import com.dne.core.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class HRCentralEmployee implements Serializable {

    private Integer id;
    private Integer userId;
    private String pid;
    private String ipin;
    private String cwid;
    private String biLastName;
    private String biFirstName;
    private String elLastName;
    private String elFirstName;
    private String gender;
    private String employeeStatus;
    private String companyCode;
    private Integer orgId;
    private String orgTxt;
    private String positionId;
    private String positionTxt;
    private String costCenter;
    private String employeeGroup;
    private String staffType;
    private String personalArea;
    private String personalAreaText;
    private String businessAddress;
    private String workingLocation;
    private Date companyEntryDate;
    private Date groupEntryDate;
    private String resourceManager;
    private String adminManager;
    private Date leavingDate;
    private String vendorCode;
    private String action;
    private String email;
    private Date lastModifyDate;
    private Date createDate;
    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIpin() {
        return ipin;
    }

    public void setIpin(String ipin) {
        this.ipin = ipin;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = StringUtils.isEmpty(cwid) ? "NA" : cwid;
    }

    public String getBiLastName() {
        return biLastName;
    }

    public void setBiLastName(String biLastName) {
        this.biLastName = biLastName == null ? null : StringUtils.trim(biLastName);
    }

    public String getBiFirstName() {
        return biFirstName;
    }

    public void setBiFirstName(String biFirstName) {
        this.biFirstName = biFirstName == null ? null : StringUtils.trim(biFirstName);
    }

    public String getElLastName() {
        return elLastName;
    }

    public void setElLastName(String elLastName) {
        this.elLastName = elLastName == null ? null : StringUtils.trim(elLastName);
    }

    public String getElFirstName() {
        return elFirstName;
    }

    public void setElFirstName(String elFirstName) {
        this.elFirstName = elFirstName == null ? null : StringUtils.trim(elFirstName);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : StringUtils.trim(gender);
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus == null ? null : StringUtils.trim(employeeStatus);
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : StringUtils.trim(companyCode);
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgTxt() {
        return orgTxt;
    }

    public void setOrgTxt(String orgTxt) {
        this.orgTxt = StringUtils.replaceUTFWhiteSpace(orgTxt);
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
        this.positionTxt = StringUtils.replaceUTFWhiteSpace(positionTxt);
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter == null ? null : StringUtils.trim(costCenter);
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup == null ? null : StringUtils.trim(employeeGroup);
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : StringUtils.trim(staffType);
    }

    public String getPersonalArea() {
        return personalArea;
    }

    public void setPersonalArea(String personalArea) {
        this.personalArea = personalArea == null ? null : StringUtils.trim(personalArea);
    }

    public String getPersonalAreaText() {
        return personalAreaText;
    }

    public void setPersonalAreaText(String personalAreaText) {
        this.personalAreaText = personalAreaText == null ? null : StringUtils.trim(personalAreaText);
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress == null ? null : StringUtils.trim(businessAddress);
    }

    public String getWorkingLocation() {
        return workingLocation;
    }

    public void setWorkingLocation(String workingLocation) {
        this.workingLocation = workingLocation == null ? null : StringUtils.trim(workingLocation);
    }

    public Date getCompanyEntryDate() {
        return companyEntryDate;
    }

    public void setCompanyEntryDate(Date companyEntryDate) {
        this.companyEntryDate = companyEntryDate;
    }

    public Date getGroupEntryDate() {
        return groupEntryDate;
    }

    public void setGroupEntryDate(Date groupEntryDate) {
        this.groupEntryDate = groupEntryDate;
    }

    public String getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(String resourceManager) {
        this.resourceManager = resourceManager == null ? null : StringUtils.trim(resourceManager);
    }

    public String getAdminManager() {
        return adminManager;
    }

    public void setAdminManager(String adminManager) {
        this.adminManager = adminManager == null ? null : StringUtils.trim(adminManager);
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode == null ? null : StringUtils.trim(vendorCode);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : StringUtils.trim(action);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : StringUtils.trim(delFlag);
    }
}
