package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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


}
