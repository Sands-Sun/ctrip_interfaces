package com.dne.ctrip.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.dne.core.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class EmployeeInfo  implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(serialize = false)
    private Integer id;
    @JSONField(serialize = false)
    private Integer sfUserId;

    @JSONField(serialize = false)
    private String ipin;
    @JSONField(name = "EmployeeID")
    private String cwid;
    @JSONField(name = "Gender")
    private String gender;
    @JSONField(name = "NameENFirstName")
    private String firstName;
    @JSONField(name = "NameENLastName")
    private String lastName;
    @JSONField(name = "Name")
    private String chineseName;
    @JSONField(name = "RankName")
    private String rankName;
    @JSONField(name = "Dept1")
    private String companyCode;
    @JSONField(name = "Dept2")
    private String orgId;
    @JSONField(name = "Dept3")
    private String orgTxt;
    @JSONField(name = "Dept4")
    private String positionId;
    @JSONField(name = "Dept5")
    private String positionTxt;
    @JSONField(serialize = false)
    private String costCenter;
    @JSONField(serialize = false)
    private String country;
    @JSONField(name = "Email")
    private String email;
    @JSONField(serialize = false)
    private Date joinDate;
    @JSONField(serialize = false)
    private String markDeleted;
    @JSONField(serialize = false)
    private Date dimissionDate;
    @JSONField(name = "WorkCity")
    private String location;
    @JSONField(serialize = false)
    private Date createdDate;
    @JSONField(serialize = false)
    private Date lastModifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.cwid = cwid == null ? null : StringUtils.trim(cwid);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : StringUtils.trim(gender);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : StringUtils.trim(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : StringUtils.trim(lastName);
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        if(StringUtils.isEmpty(chineseName)){
            this.chineseName = StringUtils.EMPTY;
        }else if(StringUtils.containsChinese(chineseName)){
            this.chineseName = StringUtils.deleteWhitespace(chineseName);
        }else {
            System.out.println("-----Not Chinese name: {}" + chineseName);
            this.chineseName = StringUtils.trim(chineseName);
        }
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
        this.companyCode = companyCode == null ? null : StringUtils.trim(companyCode);
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = StringUtils.trim(costCenter);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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
        this.orgTxt = orgTxt == null ? null : StringUtils.trim(orgTxt);
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : StringUtils.trim(positionId);
    }

    public String getPositionTxt() {
        return positionTxt;
    }

    public void setPositionTxt(String positionTxt) {
        this.positionTxt = positionTxt == null ? null : StringUtils.trim(positionTxt);
    }

    public String getMarkDeleted() {
        return markDeleted;
    }

    public void setMarkDeleted(String markDeleted) {
        this.markDeleted = markDeleted == null ? null : StringUtils.trim(markDeleted);
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
        this.location = location == null ? null : StringUtils.trim(location);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
