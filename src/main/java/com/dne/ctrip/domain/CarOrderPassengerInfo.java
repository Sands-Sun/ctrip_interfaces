package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class CarOrderPassengerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "PassengerName")
    private String passengerName;	//	出行人姓名
    @JSONField(name = "PassengerPhone")
    private String passengerPhone;	//	出行人手机号
    @JSONField(name = "PassengerType")
    private String passengerType;	//	出行人类别(P:乘客D:驾驶员)
    @JSONField(name = "UserProperties")
    private String userProperties;	//	出行人属性(VIP,Boss,C)
    @JSONField(name = "EmployeeID")
    private String employeeID;		//	员工编号
    @JSONField(name = "CorpUserID")
    private String corpUserID;		//	携程卡号
    @JSONField(name = "IdNumber")
    private String idNumber;		//	证件号码
    @JSONField(name = "IdType")
    private String idType;			//	证件类型(不为空的情况下证件号码必须填写)1:身份证,2:护照,4:军人证,7:回乡证,8:台胞证,10:港澳通行证,99:其它
    @JSONField(name = "CityId")
    private String cityId;			//	城市ID
    @JSONField(name = "CityName")
    private String cityName;		//	城市名称
    @JSONField(name = "CostCenter1")
    private String costCenter1;		//	成本中心一
    @JSONField(name = "CostCenter2")
    private String costCenter2;		//	成本中心二
    @JSONField(name = "CostCenter3")
    private String costCenter3;		//	成本中心三
    @JSONField(name = "CostCenter4")
    private String costCenter4;		//	成本中心四
    @JSONField(name = "CostCenter5")
    private String costCenter5;		//	成本中心五
    @JSONField(name = "CostCenter6")
    private String costCenter6;		//	成本中心六
    @JSONField(name = "CurrentStatus")
    private String currentStatus;	//	当前状态（1:有效0:无效）
    @JSONField(name = "Dept1")
    private String dept1;			//	部门一
    @JSONField(name = "Dept2")
    private String dept2;			//	部门二
    @JSONField(name = "Dept3")
    private String dept3;			//	部门三
    @JSONField(name = "Dept4")
    private String dept4;			//	部门四
    @JSONField(name = "Dept5")
    private String dept5;			//	部门五
    @JSONField(name = "Dept6")
    private String dept6;			//	部门六
    @JSONField(name = "Dept7")
    private String dept7;			//	部门七
    @JSONField(name = "Dept8")
    private String dept8;			//	部门八
    @JSONField(name = "Dept9")
    private String dept9;			//	部门九
    @JSONField(name = "Dept10")
    private String dept10;			//	部门十

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(String userProperties) {
        this.userProperties = userProperties;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getCorpUserID() {
        return corpUserID;
    }

    public void setCorpUserID(String corpUserID) {
        this.corpUserID = corpUserID;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCostCenter1() {
        return costCenter1;
    }

    public void setCostCenter1(String costCenter1) {
        this.costCenter1 = costCenter1;
    }

    public String getCostCenter2() {
        return costCenter2;
    }

    public void setCostCenter2(String costCenter2) {
        this.costCenter2 = costCenter2;
    }

    public String getCostCenter3() {
        return costCenter3;
    }

    public void setCostCenter3(String costCenter3) {
        this.costCenter3 = costCenter3;
    }

    public String getCostCenter4() {
        return costCenter4;
    }

    public void setCostCenter4(String costCenter4) {
        this.costCenter4 = costCenter4;
    }

    public String getCostCenter5() {
        return costCenter5;
    }

    public void setCostCenter5(String costCenter5) {
        this.costCenter5 = costCenter5;
    }

    public String getCostCenter6() {
        return costCenter6;
    }

    public void setCostCenter6(String costCenter6) {
        this.costCenter6 = costCenter6;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getDept1() {
        return dept1;
    }

    public void setDept1(String dept1) {
        this.dept1 = dept1;
    }

    public String getDept2() {
        return dept2;
    }

    public void setDept2(String dept2) {
        this.dept2 = dept2;
    }

    public String getDept3() {
        return dept3;
    }

    public void setDept3(String dept3) {
        this.dept3 = dept3;
    }

    public String getDept4() {
        return dept4;
    }

    public void setDept4(String dept4) {
        this.dept4 = dept4;
    }

    public String getDept5() {
        return dept5;
    }

    public void setDept5(String dept5) {
        this.dept5 = dept5;
    }

    public String getDept6() {
        return dept6;
    }

    public void setDept6(String dept6) {
        this.dept6 = dept6;
    }

    public String getDept7() {
        return dept7;
    }

    public void setDept7(String dept7) {
        this.dept7 = dept7;
    }

    public String getDept8() {
        return dept8;
    }

    public void setDept8(String dept8) {
        this.dept8 = dept8;
    }

    public String getDept9() {
        return dept9;
    }

    public void setDept9(String dept9) {
        this.dept9 = dept9;
    }

    public String getDept10() {
        return dept10;
    }

    public void setDept10(String dept10) {
        this.dept10 = dept10;
    }
}
