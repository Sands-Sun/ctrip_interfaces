package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CompanyNameManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String companyNameCn;

    private String companyNameEn;

    private String companyCode;

    private Boolean isDelete;

}
