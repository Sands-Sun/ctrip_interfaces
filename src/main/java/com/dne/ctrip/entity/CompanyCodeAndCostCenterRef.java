package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CompanyCodeAndCostCenterRef implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyCode;
    private String costCenter;
    private String refId;  // OrderId, cwId, CenterCode
    private String extraRef; // email

}
