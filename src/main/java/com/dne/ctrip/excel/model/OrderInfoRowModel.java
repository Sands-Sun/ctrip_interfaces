package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class OrderInfoRowModel extends BaseRowModel {

    @ExcelProperty(value = "VASOID", index = 0)
    private String orderId;// 订单ID

    @ExcelProperty(value = "OrderStatus",index = 1)
    private String orderStatus; // 订单状态

    @ExcelProperty(value = "CtripCardNo",index = 2)
    private String cardNo; // 卡号

    @ExcelProperty(value = "Name",index = 3)
    private String callUserName; // 下单人姓名

    @ExcelProperty(value = "OrderType",index = 4)
    private String useCarMode; // orderType 订单类型（1:国内接送机2:国际接送机3:国内包车4:国内租车6:随叫随到）

    @ExcelProperty(value = "VehicleType",index = 5)
    private String useCarType;// 用车类型

    @ExcelProperty(value = "OrderDate",index = 6)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date orderTime;// 下单时间

    @ExcelProperty(value = "TakeoffDate",index = 7)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date planTime;// 计划用车时间

    @ExcelProperty(value = "ServiceContent",index = 8)
    private String serviceContent; //服务内容

    @ExcelProperty(value = "PassengerName",index = 9)
    private String passengerName;// 乘车姓名

    @ExcelProperty(value = "TicketNum",index = 10)
    private String ticketNum; //数量

    @ExcelProperty(value = "Amount",index = 11)
    private String orderTotalPrice;// 订单总金额

    @ExcelProperty(value = "Commissionfee",index = 12)
    private String carAddTaxAmount; //手续费

    @ExcelProperty(value = "CarValueAddFee",index = 13)
    private String carValueAddFee; //增值费用

    @ExcelProperty(value = "ExpressFee",index = 14)
    private String expressFee;  //快递费

    @ExcelProperty(value = "PenaltyFee",index = 15)
    private String penaltyFee; //违约金

    @ExcelProperty(value = "PostServiceFee",index = 16)
    private String postServiceFee; //商旅管理服务费

    @ExcelProperty(value = "RealAmountHasPost",index = 17)
    private String companyRealPay; //实收实付（含后收） realAmountHasPost

    @ExcelProperty(value = "PrePayType",index = 18)
    private String paymentType; //结算方式

    @ExcelProperty(value = "ContactName",index = 19)
    private String contactName; // 联系人姓名

    @ExcelProperty(value = "EmployeeID",index = 20)
    private String callUserEmployeeId; //员工编号 cwid

    @ExcelProperty(value = "CostCenter",index = 21)
    private String costCenterName; //成本中心

    @ExcelProperty(value = "CostCenter2",index = 22)
    private String costCenter2; //成本中心2

    @ExcelProperty(value = "CostCenter3",index = 23)
    private String costCenter3; //成本中心3

    @ExcelProperty(value = "JourneyNo",index = 24)
    private String journeyNo;	//	关联行程号

    @ExcelProperty(value = "Dept1",index = 25)
    private String departmentName;// 部门名称

    @ExcelProperty(value = "Dept2",index = 26)
    private String callUserCompanyCode;// 下单人公司

    @ExcelProperty(value = "Dept3",index = 27)
    private String departmentName3; //

    @ExcelProperty(value = "ProjectName",index = 28)
    private String projectName; //项目

    @ExcelProperty(value = "VendorName",index = 29)
    private String vendorName; //供应商名称

    @ExcelProperty(value = "DefineValue1",index = 30)
    private String defineValue1; //自定义字段1

    @ExcelProperty(value = "DefineValue2",index = 31)
    private String defineValue2; //自定义字段2

    @ExcelProperty(value = "Dept4",index = 32)
    private String callUserEmployeeIpin; //部门4 departmentName4  IPIN 员工号码（员工编号）

    @ExcelProperty(value = "Dept5",index = 33)
    private String callUserPhone; //部门5   departmentName5 下单人电话

    @ExcelProperty(value = "CarBasicFeeDetail",index = 34)
    private String carBasicFeeDetail; //用车基础费明细

    @ExcelProperty(value = "CarValueAddFeeDetail",index = 35)
    private String carValueAddFeeDetail; //用车增值费用明细

    @ExcelProperty(value = "WorkCity",index = 36)
    private String workCity; //工作城市

    @ExcelProperty(value = "StartCityName",index = 37)
    private String callContry;// 用车城市  startCityName

    @ExcelProperty(value = "FixedLocationName",index = 38)
    private String fixedLocationName; //接送地

    @ExcelProperty(value = "EndCityName",index = 39)
    private String endCityName; //还车城市

    @ExcelProperty(value = "VehicleTypeFlightTrainNum",index = 40)
    private String vehicleName;	//车型名称 经济型，舒适型，豪华型，商务型，出租车，优享型

    @ExcelProperty(value = "EndTime",index = 41)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime; //用车结束时间

    @ExcelProperty(value = "TripId",index = 42)
    private String tripId; //行程号

    @ExcelProperty(value = "EndorsementNo",index = 43)
    private String endorsementNo; //出差申请单号

    @ExcelProperty(value = "CostCenter4",index = 44)
    private String costCenter4; //成本中心4

    @ExcelProperty(value = "CostCenter5",index = 45)
    private String costCenter5; //成本中心5

    @ExcelProperty(value = "JounaryReason",index = 46)
    private String useCarRemark; // 出行目的

    @ExcelProperty(value = "AuthorizeStatus",index = 47)
    private String authorizeStatus; //授权状态

    @ExcelProperty(value = "personamount",index = 48)
    private String personalPayment;// 个人支付金额 OrderDetail.OrderBaseInfo.PersonAmount

    @ExcelProperty(value = "DepartAddressDetail",index = 49)
    private String startPlace;// 上车地点

    @ExcelProperty(value = "ArriveAddressDetail",index = 50)
    private String destination;// 下车地点

    @ExcelProperty(value = "StartTime",index = 51)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime; //用车开始时间

    @ExcelProperty(value = "RealDepartAddressDetail",index = 52)
    private String realStartPlace;//实际上车地点

    @ExcelProperty(value = "RealArriveAddressDetail",index = 53)
    private String realDestination;//实际下车地点

    @ExcelProperty(value = "VendorfeeOrgin",index = 54)
    private String vendorfeeOrgin; //原始里程费

    @ExcelProperty(value = "NormalFee",index = 55)
    private String normalFee;// 行驶距离

    @ExcelProperty(value = "SceneName",index = 56)
    private String sceneName; //用车场景

    @ExcelProperty(value = "CostCenter6",index = 57)
    private String costCenter6; //成本中心6

    @ExcelProperty(value = "Rank",index = 58)
    private String rank; //职级

    @ExcelProperty(value = "Job",index = 59)
    private String job; //职务

    @ExcelProperty(value = "RealServiceBeginTime",index = 60)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginChargeTime;// 计费开始时间

    @ExcelProperty(value = "RealServiceEndTime",index = 61)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endChargeTime;// 计费结束时间

    @ExcelProperty(value = "RealDistanceKm",index = 62)
    private String driveDistance;// 行驶距离

    @ExcelProperty(value = "RealDurationMinute",index = 63)
    private String realDurationMinute; //实际行驶时长

    @ExcelProperty(value = "EndorsementName",index = 64)
    private String additionalRemarks; //出差事由

    @ExcelProperty(value = "personFeeDetail",index = 65)
    private String personFeeDetail; //个人支付明细

    @Override
    public String toString() {
        return "OrderInfoRowModel{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", callUserName='" + callUserName + '\'' +
                ", useCarMode='" + useCarMode + '\'' +
                ", useCarType='" + useCarType + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", planTime='" + planTime + '\'' +
                ", serviceContent='" + serviceContent + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", ticketNum='" + ticketNum + '\'' +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                ", carAddTaxAmount='" + carAddTaxAmount + '\'' +
                ", carValueAddFee='" + carValueAddFee + '\'' +
                ", expressFee='" + expressFee + '\'' +
                ", penaltyFee='" + penaltyFee + '\'' +
                ", postServiceFee='" + postServiceFee + '\'' +
                ", companyRealPay='" + companyRealPay + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", contactName='" + contactName + '\'' +
                ", callUserEmployeeId='" + callUserEmployeeId + '\'' +
                ", costCenterName='" + costCenterName + '\'' +
                ", costCenter2='" + costCenter2 + '\'' +
                ", costCenter3='" + costCenter3 + '\'' +
                ", journeyNo='" + journeyNo + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", callUserCompanyCode='" + callUserCompanyCode + '\'' +
                ", departmentName3='" + departmentName3 + '\'' +
                ", projectName='" + projectName + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", defineValue1='" + defineValue1 + '\'' +
                ", defineValue2='" + defineValue2 + '\'' +
                ", callUserEmployeeIpin='" + callUserEmployeeIpin + '\'' +
                ", callUserPhone='" + callUserPhone + '\'' +
                ", carBasicFeeDetail='" + carBasicFeeDetail + '\'' +
                ", carValueAddFeeDetail='" + carValueAddFeeDetail + '\'' +
                ", workCity='" + workCity + '\'' +
                ", callContry='" + callContry + '\'' +
                ", fixedLocationName='" + fixedLocationName + '\'' +
                ", endCityName='" + endCityName + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", receivingTime='" + receivingTime + '\'' +
                ", tripId='" + tripId + '\'' +
                ", endorsementNo='" + endorsementNo + '\'' +
                ", costCenter4='" + costCenter4 + '\'' +
                ", costCenter5='" + costCenter5 + '\'' +
                ", useCarRemark='" + useCarRemark + '\'' +
                ", authorizeStatus='" + authorizeStatus + '\'' +
                ", personalPayment='" + personalPayment + '\'' +
                ", startPlace='" + startPlace + '\'' +
                ", destination='" + destination + '\'' +
                ", startTime='" + startTime + '\'' +
                ", realStartPlace='" + realStartPlace + '\'' +
                ", realDestination='" + realDestination + '\'' +
                ", vendorfeeOrgin='" + vendorfeeOrgin + '\'' +
                ", normalFee='" + normalFee + '\'' +
                ", sceneName='" + sceneName + '\'' +
                ", costCenter6='" + costCenter6 + '\'' +
                ", rank='" + rank + '\'' +
                ", job='" + job + '\'' +
                ", beginChargeTime='" + beginChargeTime + '\'' +
                ", endChargeTime='" + endChargeTime + '\'' +
                ", driveDistance='" + driveDistance + '\'' +
                ", realDurationMinute='" + realDurationMinute + '\'' +
                ", additionalRemarks='" + additionalRemarks + '\'' +
                ", personFeeDetail='" + personFeeDetail + '\'' +
                '}';
    }
}
