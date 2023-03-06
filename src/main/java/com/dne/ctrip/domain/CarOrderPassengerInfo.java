package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
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

}
