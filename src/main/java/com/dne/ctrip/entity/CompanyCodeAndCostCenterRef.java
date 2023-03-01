package com.dne.ctrip.entity;

import java.io.Serializable;

public class CompanyCodeAndCostCenterRef implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyCode;
    private String costCenter;
    private String refId;  // OrderId, cwId, CenterCode
    private String extraRef; // email

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getExtraRef() {
        return extraRef;
    }

    public void setExtraRef(String extraRef) {
        this.extraRef = extraRef;
    }
}
