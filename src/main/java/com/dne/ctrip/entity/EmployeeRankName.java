package com.dne.ctrip.entity;

import java.io.Serializable;

public class EmployeeRankName implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String cwid;
    private String rankName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCwid() {
        return cwid;
    }

    public void setCwid(String cwid) {
        this.cwid = cwid;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
