package com.dne.ctrip.entity;

import java.io.Serializable;

public class CompanyNameManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String companyNameCn;

    private String companyNameEn;

    private String companyCode;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyNameCn() {
        return companyNameCn;
    }

    public void setCompanyNameCn(String companyNameCn) {
        this.companyNameCn = companyNameCn;
    }

    public String getCompanyNameEn() {
        return companyNameEn;
    }

    public void setCompanyNameEn(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
