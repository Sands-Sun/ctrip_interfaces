package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ActiveEmpInfoRowModel  extends BaseRowModel implements IBaseEmpInfoRowModel{

    @ExcelProperty(value = "Employee Category", index = 0)
    private String category;

    @ExcelProperty(value = "IPIN", index = 1)
    private String ipin;

    @ExcelProperty(value = "Employee Name (CN)", index = 2)
    private String cnName;

    @ExcelProperty(value = "Employee Name (EN)", index = 3)
    private String enName;

    @ExcelProperty(value = "CWID", index = 4)
    private String cwid;

    @ExcelProperty(value = "Gender", index = 5)
    private String gender;

    @ExcelProperty(value = "Employee Type", index = 6)
    private String type;

    @ExcelProperty(value = "Adjusted ServiceDate", index = 7)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date adjustedServiceDate;

    @ExcelProperty(value = "Adjusted Service Year", index = 8)
    private String adjustedServiceYear;

    @ExcelProperty(value = "Company Entry Date", index = 9)
    private Date companyEntryDate;

    @ExcelProperty(value = "Company Service Year", index = 10)
    private String companyServiceYear;

    @ExcelProperty(value = "Contractor start day", index = 11)
    private Date contractorStartDay;

    @ExcelProperty(value = "ORG1", index = 12)
    private String org1; //company_code + company_name

    @ExcelProperty(value = "ORG2", index = 13)
    private String org2;

    @ExcelProperty(value = "ORG3", index = 14)
    private String org3;

    @ExcelProperty(value = "ORG4", index = 15)
    private String org4;

    @ExcelProperty(value = "ORG5", index = 16)
    private String org5;

    @ExcelProperty(value = "ORG6", index = 17)
    private String org6;

    @ExcelProperty(value = "ORG7", index = 18)
    private String org7;

    @ExcelProperty(value = "ORG8", index = 19)
    private String org8;

    @ExcelProperty(value = "ORG9", index = 20)
    private String org9;

    @ExcelProperty(value = "Position ID", index = 21)
    private String positionId;

    @ExcelProperty(value = "Position Name", index = 22)
    private String positionTxt;

    @ExcelProperty(value = "Chinese Title", index = 23)
    private String chineseTitle;

    @ExcelProperty(value = "Abbrv. of position", index = 24)
    private String abbrvOfPosition;

    @ExcelProperty(value = "Org.Unit Code", index = 25)
    private String orgId;

    @ExcelProperty(value = "Org.Unit", index = 26)
    private String orgTxt;

    @ExcelProperty(value = "Cost Center", index = 27)
    private String costCenter;

    @ExcelProperty(value = "Cost Center Name", index = 28)
    private String costCenterName;

    @ExcelProperty(value = "Resource Manager (EN)", index = 29)
    private String  resourceManagerEnName;

    @ExcelProperty(value = "Resource Manager IPIN", index = 30)
    private String  resourceManagerIpin;

    @ExcelProperty(value = "Resource manager CWID", index = 31)
    private String  resourceManagerCwid;

    @ExcelProperty(value = "Admin Manager (EN)", index = 32)
    private String  adminManagerEnName;

    @ExcelProperty(value = "Admin Manager IPIN", index = 33)
    private String  adminManagerIpin;

    @ExcelProperty(value = "Admin Manager CWID", index = 34)
    private String  adminManagerCwid;

    @ExcelProperty(value = "Chinese Province", index = 35)
    private String province;

    @ExcelProperty(value = "Chinese City", index = 36)
    private String city;

    @ExcelProperty(value = "Business Address_ZH", index = 37)
    private String businessAddress;

    @ExcelProperty(value = "Email Address", index = 38)
    private String email;
    @Override
    public String toString() {
        return "ActiveEmpInfoRowModel{" +
                "category='" + category + '\'' +
                ", ipin='" + ipin + '\'' +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", cwid='" + cwid + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", adjustedServiceDate=" + adjustedServiceDate +
                ", adjustedServiceYear='" + adjustedServiceYear + '\'' +
                ", companyEntryDate=" + companyEntryDate +
                ", companyServiceYear='" + companyServiceYear + '\'' +
                ", contractorStartDay=" + contractorStartDay +
                ", org1='" + org1 + '\'' +
                ", org2='" + org2 + '\'' +
                ", org3='" + org3 + '\'' +
                ", org4='" + org4 + '\'' +
                ", org5='" + org5 + '\'' +
                ", org6='" + org6 + '\'' +
                ", org7='" + org7 + '\'' +
                ", org8='" + org8 + '\'' +
                ", org9='" + org9 + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionTxt='" + positionTxt + '\'' +
                ", chineseTitle='" + chineseTitle + '\'' +
                ", abbrvOfPosition='" + abbrvOfPosition + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgTxt='" + orgTxt + '\'' +
                ", costCenter='" + costCenter + '\'' +
                ", costCenterName='" + costCenterName + '\'' +
                ", resourceManagerEnName='" + resourceManagerEnName + '\'' +
                ", resourceManagerIpin='" + resourceManagerIpin + '\'' +
                ", resourceManagerCwid='" + resourceManagerCwid + '\'' +
                ", adminManagerEnName='" + adminManagerEnName + '\'' +
                ", adminManagerIpin='" + adminManagerIpin + '\'' +
                ", adminManagerCwid='" + adminManagerCwid + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
