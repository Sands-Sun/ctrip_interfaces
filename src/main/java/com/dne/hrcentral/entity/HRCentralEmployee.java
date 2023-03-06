package com.dne.hrcentral.entity;


import com.dne.core.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
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


    public void setCwid(String cwid) {
        this.cwid = StringUtils.isEmpty(cwid) ? "NA" : cwid;
    }

    public void setBiLastName(String biLastName) {
        this.biLastName = biLastName == null ? null : StringUtils.trim(biLastName);
    }

    public void setBiFirstName(String biFirstName) {
        this.biFirstName = biFirstName == null ? null : StringUtils.trim(biFirstName);
    }

    public void setElLastName(String elLastName) {
        this.elLastName = elLastName == null ? null : StringUtils.trim(elLastName);
    }

    public void setElFirstName(String elFirstName) {
        this.elFirstName = elFirstName == null ? null : StringUtils.trim(elFirstName);
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : StringUtils.trim(gender);
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus == null ? null : StringUtils.trim(employeeStatus);
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : StringUtils.trim(companyCode);
    }
    public void setOrgTxt(String orgTxt) {
        this.orgTxt = StringUtils.replaceUTFWhiteSpace(orgTxt);
    }

    public void setPositionTxt(String positionTxt) {
        this.positionTxt = StringUtils.replaceUTFWhiteSpace(positionTxt);
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter == null ? null : StringUtils.trim(costCenter);
    }
    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup == null ? null : StringUtils.trim(employeeGroup);
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType == null ? null : StringUtils.trim(staffType);
    }

    public void setPersonalArea(String personalArea) {
        this.personalArea = personalArea == null ? null : StringUtils.trim(personalArea);
    }

    public void setPersonalAreaText(String personalAreaText) {
        this.personalAreaText = personalAreaText == null ? null : StringUtils.trim(personalAreaText);
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress == null ? null : StringUtils.trim(businessAddress);
    }

    public void setWorkingLocation(String workingLocation) {
        this.workingLocation = workingLocation == null ? null : StringUtils.trim(workingLocation);
    }

    public void setCompanyEntryDate(Date companyEntryDate) {
        this.companyEntryDate = companyEntryDate;
    }

    public void setGroupEntryDate(Date groupEntryDate) {
        this.groupEntryDate = groupEntryDate;
    }

    public void setResourceManager(String resourceManager) {
        this.resourceManager = resourceManager == null ? null : StringUtils.trim(resourceManager);
    }

    public void setAdminManager(String adminManager) {
        this.adminManager = adminManager == null ? null : StringUtils.trim(adminManager);
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode == null ? null : StringUtils.trim(vendorCode);
    }
    public void setAction(String action) {
        this.action = action == null ? null : StringUtils.trim(action);
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : StringUtils.trim(delFlag);
    }
}
