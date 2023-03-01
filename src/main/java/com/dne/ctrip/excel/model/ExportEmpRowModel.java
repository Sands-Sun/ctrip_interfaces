package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class ExportEmpRowModel extends BaseRowModel {

    @ExcelProperty(value = "User Id", index = 0)
    private Integer sfUserId;
    @ExcelProperty(value = "Ipin", index = 1)
    private String ipin;
    @ExcelProperty(value = "Cwid", index = 2)
    private String cwid;
    @ExcelProperty(value = "Gender", index = 3)
    private String gender;
    @ExcelProperty(value = "First Name", index = 4)
    private String firstName;
    @ExcelProperty(value = "Last Name", index = 5)
    private String lastName;
    @ExcelProperty(value = "Chinese Name", index = 6)
    private String chineseName;
    @ExcelProperty(value = "Rank Name", index = 7)
    private String rankName;
    @ExcelProperty(value = "Company Code", index = 8)
    private String companyCode;
    @ExcelProperty(value = "Org Id", index = 9)
    private String orgId;
    @ExcelProperty(value = "Org Txt", index = 10)
    private String orgTxt;
    @ExcelProperty(value = "Position Id", index = 11)
    private String positionId;
    @ExcelProperty(value = "Position Txt", index = 12)
    private String positionTxt;
    @ExcelProperty(value = "Work Location", index = 13)
    private String location;
    @ExcelProperty(value = "Cost Center", index = 14)
    private String costCenter;
    @ExcelProperty(value = "Email Address", index = 15)
    private String email;
    @ExcelProperty(value = "Mark Deleted", index = 16)
    private String markDeleted;
    @ExcelProperty(value = "Dimission Date", index = 17)
    private Date dimissionDate;


    public Integer getSfUserId() {
        return sfUserId;
    }

    public void setSfUserId(Integer sfUserId) {
        this.sfUserId = sfUserId;
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
        this.cwid = cwid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgTxt() {
        return orgTxt;
    }

    public void setOrgTxt(String orgTxt) {
        this.orgTxt = orgTxt;
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

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
