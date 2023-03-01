package com.dne.hrcentral.dao;

import com.dne.hrcentral.entity.HRCentralEmployee;

import java.util.List;

public interface HrCentralDao {
    List<HRCentralEmployee> getEmployeeInfoList(String synAccount);
}
