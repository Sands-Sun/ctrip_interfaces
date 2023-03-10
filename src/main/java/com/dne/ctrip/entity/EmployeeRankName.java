package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmployeeRankName implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cwid;

    private String rankName;

}
