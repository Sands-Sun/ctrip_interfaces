package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class ExportChangeEmpRowModel extends BaseRowModel {


    @ExcelProperty(value = "Current User Id", index = 0)
    private Integer currentSfUserId;
    @ExcelProperty(value = "Previous User Id", index = 1)
    private Integer pervSfUserId;
    @ExcelProperty(value = "User Id Change", index = 2)
    private Boolean sfUserIdChange;

    @ExcelProperty(value = "Current Ipin", index = 3)
    private String currentIpin;
    @ExcelProperty(value = "Previous Ipin", index = 4)
    private String pervIpin;
    @ExcelProperty(value = "Ipin Change", index = 5)
    private Boolean ipinChange;


    @ExcelProperty(value = "Current Cwid", index = 6)
    private String currentCwid;
    @ExcelProperty(value = "Previous Cwid", index = 7)
    private String pervCwid;
    @ExcelProperty(value = "Cwid Change", index = 8)
    private Boolean cwidChange;

    @ExcelProperty(value = "Current Gender", index = 9)
    private String currentGender;
    @ExcelProperty(value = "Previous Gender", index = 10)
    private String pervGender;
    @ExcelProperty(value = "Gender Change", index = 11)
    private Boolean genderChange;


    @ExcelProperty(value = "First Name", index = 4)
    private String currentFirstName;
    @ExcelProperty(value = "Last Name", index = 5)
    private String currentLastName;
    @ExcelProperty(value = "Chinese Name", index = 6)
    private String currentChineseName;
    @ExcelProperty(value = "Rank Name", index = 7)
    private String currentRankName;
    @ExcelProperty(value = "Company Code", index = 8)
    private String currentCompanyCode;
    @ExcelProperty(value = "Org Id", index = 9)
    private String currentOrgId;
    @ExcelProperty(value = "Org Txt", index = 10)
    private String currentOrgTxt;
    @ExcelProperty(value = "Position Id", index = 11)
    private String currentPositionId;
    @ExcelProperty(value = "Position Txt", index = 12)
    private String currentPositionTxt;
    @ExcelProperty(value = "Work Location", index = 13)
    private String currentLocation;
    @ExcelProperty(value = "Cost Center", index = 14)
    private String currentCostCenter;
    @ExcelProperty(value = "Email Address", index = 15)
    private String currentEmail;
    @ExcelProperty(value = "Mark Deleted", index = 16)
    private String currentMarkDeleted;
    @ExcelProperty(value = "Dimission Date", index = 17)
    private Date currentDimissionDate;

}
