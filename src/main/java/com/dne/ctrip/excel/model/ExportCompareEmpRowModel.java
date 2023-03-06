package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.dne.core.util.StringUtils;

import java.util.Date;

public class ExportCompareEmpRowModel extends BaseRowModel {


    @ExcelProperty(value = "Current User Id", index = 0)
    private Integer sfUserId;
    @ExcelProperty(value = "Previous User Id", index = 1)
    private Integer prevSfUserId;
    @ExcelProperty(value = "User Id Change Flag", index = 2)
    private Boolean sfUserIdChange;

    @ExcelProperty(value = "Current Ipin", index = 3)
    private String ipin;
    @ExcelProperty(value = "Previous Ipin", index = 4)
    private String prevIpin;
    @ExcelProperty(value = "Ipin Change Flag", index = 5)
    private Boolean ipinChange;


    @ExcelProperty(value = "Current Cwid", index = 6)
    private String cwid;
    @ExcelProperty(value = "Previous Cwid", index = 7)
    private String prevCwid;
    @ExcelProperty(value = "Cwid Change Flag", index = 8)
    private Boolean cwidChange;

    @ExcelProperty(value = "Current Gender", index = 9)
    private String gender;
    @ExcelProperty(value = "Previous Gender", index = 10)
    private String prevGender;
    @ExcelProperty(value = "Gender Change Flag", index = 11)
    private Boolean genderChange;


    @ExcelProperty(value = "Current First Name", index = 12)
    private String firstName;
    @ExcelProperty(value = "Previous First Name", index = 13)
    private String prevFirstName;
    @ExcelProperty(value = "First Name Change Flag", index = 14)
    private Boolean firstNameChange;

    @ExcelProperty(value = "Current Last Name", index = 15)
    private String lastName;
    @ExcelProperty(value = "Previous Last Name", index = 16)
    private String prevLastName;
    @ExcelProperty(value = "Last Name Change Flag", index = 17)
    private Boolean lastNameChange;


    @ExcelProperty(value = "Current Chinese Name", index = 18)
    private String chineseName;
    @ExcelProperty(value = "Previous Chinese Name", index = 19)
    private String prevChineseName;
    @ExcelProperty(value = "Chinese Name Change Flag", index = 20)
    private Boolean chineseNameChange;


    @ExcelProperty(value = "Current Rank Name", index = 21)
    private String rankName;
    @ExcelProperty(value = "Previous Rank Name", index = 22)
    private String prevRankName;
    @ExcelProperty(value = "Rank Name Change Flag", index = 23)
    private Boolean rankNameChange;


    @ExcelProperty(value = "Current Company Code", index = 24)
    private String companyCode;
    @ExcelProperty(value = "Previous Company Code", index = 25)
    private String prevCompanyCode;
    @ExcelProperty(value = "Company Code Change Flag", index = 26)
    private Boolean companyCodeChange;


    @ExcelProperty(value = "Current Org Id", index = 27)
    private String orgId;
    @ExcelProperty(value = "Previous Org Id", index = 28)
    private String prevOrgId;
    @ExcelProperty(value = "Org Id Change Flag", index = 29)
    private Boolean orgIdChange;


    @ExcelProperty(value = "Current Org Txt", index = 30)
    private String orgTxt;
    @ExcelProperty(value = "Previous Org Txt", index = 31)
    private String prevOrgTxt;
    @ExcelProperty(value = "Org Txt Change Flag", index = 32)
    private Boolean orgTxtChange;


    @ExcelProperty(value = "Current Position Id", index = 33)
    private String positionId;
    @ExcelProperty(value = "Previous Position Id", index = 34)
    private String prevPositionId;
    @ExcelProperty(value = "Position Id Change Flag", index = 35)
    private Boolean positionIdChange;


    @ExcelProperty(value = "Current Position Txt", index = 36)
    private String positionTxt;
    @ExcelProperty(value = "Previous Position Txt", index = 37)
    private String prevPositionTxt;
    @ExcelProperty(value = "Position Txt Change Flag", index = 38)
    private Boolean positionTxtChange;


    @ExcelProperty(value = "Current Work Location", index = 39)
    private String location;
    @ExcelProperty(value = "Previous Work Location", index = 40)
    private String prevLocation;
    @ExcelProperty(value = "Work Location Change Flag", index = 41)
    private Boolean locationChange;


    @ExcelProperty(value = "Current Cost Center", index = 42)
    private String costCenter;
    @ExcelProperty(value = "Previous Cost Center", index = 43)
    private String prevCostCenter;
    @ExcelProperty(value = "Cost Center Change Flag", index = 44)
    private Boolean costCenterChange;



    @ExcelProperty(value = "Current Email Address", index = 45)
    private String email;
    @ExcelProperty(value = "Previous Email Address", index = 46)
    private String prevEmail;
    @ExcelProperty(value = "Email Address Change Flag", index = 47)
    private Boolean emailChange;


    @ExcelProperty(value = "Mark Deleted", index = 48)
    private String markDeleted;

    @ExcelProperty(value = "Dimission Date", index = 49)
    private Date dimissionDate;

    public Integer getSfUserId() {
        return sfUserId;
    }

    public void setSfUserId(Integer sfUserId) {
        this.sfUserId = sfUserId;
    }

    public Integer getPrevSfUserId() {
        return prevSfUserId;
    }

    public void setPrevSfUserId(Integer prevSfUserId) {
        this.prevSfUserId = prevSfUserId;
    }

    public Boolean getSfUserIdChange() {
        if(sfUserId == null || prevSfUserId == null){
            return false;
        }
        return !sfUserId.equals(prevSfUserId);
    }

    public String getIpin() {
        return ipin;
    }

    public void setIpin(String ipin) {
        this.ipin = ipin;
    }

    public String getPrevIpin() {
        return prevIpin;
    }

    public void setPrevIpin(String prevIpin) {
        this.prevIpin = prevIpin;
    }

    public Boolean getIpinChange() {
        if(StringUtils.isEmpty(ipin) || StringUtils.isEmpty(prevIpin)){
            return false;
        }
        return !ipin.equals(prevIpin);
    }


    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    public String getPrevCwid() {
        return prevCwid;
    }

    public void setPrevCwid(String prevCwid) {
        this.prevCwid = prevCwid;
    }

    public Boolean getCwidChange() {
        if(StringUtils.isEmpty(cwid) || StringUtils.isEmpty(prevCwid)){
            return false;
        }
        return !cwid.equals(prevCwid);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrevGender() {
        return prevGender;
    }

    public void setPrevGender(String prevGender) {
        this.prevGender = prevGender;
    }

    public Boolean getGenderChange() {
        return !gender.equals(prevGender);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrevFirstName() {
        return prevFirstName;
    }

    public void setPrevFirstName(String prevFirstName) {
        this.prevFirstName = prevFirstName;
    }

    public Boolean getFirstNameChange() {
        if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(prevFirstName)){
            return false;
        }
        return !firstName.equals(prevFirstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrevLastName() {
        return prevLastName;
    }

    public void setPrevLastName(String prevLastName) {
        this.prevLastName = prevLastName;
    }

    public Boolean getLastNameChange() {
        if(StringUtils.isEmpty(lastName) || StringUtils.isEmpty(prevLastName)){
            return false;
        }
        return !lastName.equals(prevLastName);
    }
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getPrevChineseName() {
        return prevChineseName;
    }

    public void setPrevChineseName(String prevChineseName) {
        this.prevChineseName = prevChineseName;
    }

    public Boolean getChineseNameChange() {
        if(StringUtils.isEmpty(chineseName) || StringUtils.isEmpty(prevChineseName)){
            return false;
        }
        return !chineseName.equals(prevChineseName);
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getPrevRankName() {
        return prevRankName;
    }

    public void setPrevRankName(String prevRankName) {
        this.prevRankName = prevRankName;
    }

    public Boolean getRankNameChange() {
        return !rankName.equals(prevRankName);
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPrevCompanyCode() {
        return prevCompanyCode;
    }

    public void setPrevCompanyCode(String prevCompanyCode) {
        this.prevCompanyCode = prevCompanyCode;
    }

    public Boolean getCompanyCodeChange() {
        if(StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(prevCompanyCode)){
            return false;
        }
        return !companyCode.equals(prevCompanyCode);
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPrevOrgId() {
        return prevOrgId;
    }

    public void setPrevOrgId(String prevOrgId) {
        this.prevOrgId = prevOrgId;
    }

    public Boolean getOrgIdChange() {
        if(StringUtils.isEmpty(orgId) || StringUtils.isEmpty(prevOrgId)){
            return false;
        }
        return !orgId.equals(prevOrgId);
    }

    public String getOrgTxt() {
        return orgTxt;
    }

    public void setOrgTxt(String orgTxt) {
        this.orgTxt = orgTxt;
    }

    public String getPrevOrgTxt() {
        return prevOrgTxt;
    }

    public void setPrevOrgTxt(String prevOrgTxt) {
        this.prevOrgTxt = prevOrgTxt;
    }

    public Boolean getOrgTxtChange() {
        if(StringUtils.isEmpty(orgTxt) || StringUtils.isEmpty(prevOrgTxt)){
            return false;
        }
        return !orgTxt.equals(prevOrgTxt);
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPrevPositionId() {
        return prevPositionId;
    }

    public void setPrevPositionId(String prevPositionId) {
        this.prevPositionId = prevPositionId;
    }

    public Boolean getPositionIdChange() {
        if(StringUtils.isEmpty(positionId) || StringUtils.isEmpty(prevPositionId)){
            return false;
        }
        return !positionId.equals(prevPositionId);
    }

    public String getPositionTxt() {
        return positionTxt;
    }

    public void setPositionTxt(String positionTxt) {
        this.positionTxt = positionTxt;
    }

    public String getPrevPositionTxt() {
        return prevPositionTxt;
    }

    public void setPrevPositionTxt(String prevPositionTxt) {
        this.prevPositionTxt = prevPositionTxt;
    }

    public Boolean getPositionTxtChange() {
        if(StringUtils.isEmpty(positionTxt) || StringUtils.isEmpty(prevPositionTxt)){
            return false;
        }
        return !positionTxt.equals(prevPositionTxt);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(String prevLocation) {
        this.prevLocation = prevLocation;
    }

    public Boolean getLocationChange() {
        if(StringUtils.isEmpty(location) || StringUtils.isEmpty(prevLocation)){
            return false;
        }
        return !location.equals(prevLocation);
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getPrevCostCenter() {
        return prevCostCenter;
    }

    public void setPrevCostCenter(String prevCostCenter) {
        this.prevCostCenter = prevCostCenter;
    }

    public Boolean getCostCenterChange() {
        if(StringUtils.isEmpty(costCenter) || StringUtils.isEmpty(prevCostCenter)){
            return false;
        }
        return !costCenter.equals(prevCostCenter);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrevEmail() {
        return prevEmail;
    }

    public void setPrevEmail(String prevEmail) {
        this.prevEmail = prevEmail;
    }

    public Boolean getEmailChange() {
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(prevEmail)){
            return false;
        }
        return !email.equals(prevEmail);
    }

    public String getMarkDeleted() {
        return markDeleted;
    }

    public void setMarkDeleted(String markDeleted) {
        this.markDeleted = markDeleted;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }
}
