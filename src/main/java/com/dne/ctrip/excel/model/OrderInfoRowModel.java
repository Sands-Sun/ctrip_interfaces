package com.dne.ctrip.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCallUserName() {
        return callUserName;
    }

    public void setCallUserName(String callUserName) {
        this.callUserName = callUserName;
    }

    public String getUseCarMode() {
        return useCarMode;
    }

    public void setUseCarMode(String useCarMode) {
        this.useCarMode = useCarMode;
    }

    public String getUseCarType() {
        return useCarType;
    }

    public void setUseCarType(String useCarType) {
        this.useCarType = useCarType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Date getBeginChargeTime() {
        return beginChargeTime;
    }

    public void setBeginChargeTime(Date beginChargeTime) {
        this.beginChargeTime = beginChargeTime;
    }

    public Date getEndChargeTime() {
        return endChargeTime;
    }

    public void setEndChargeTime(Date endChargeTime) {
        this.endChargeTime = endChargeTime;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getCarAddTaxAmount() {
        return carAddTaxAmount;
    }

    public void setCarAddTaxAmount(String carAddTaxAmount) {
        this.carAddTaxAmount = carAddTaxAmount;
    }

    public String getCarValueAddFee() {
        return carValueAddFee;
    }

    public void setCarValueAddFee(String carValueAddFee) {
        this.carValueAddFee = carValueAddFee;
    }

    public String getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(String expressFee) {
        this.expressFee = expressFee;
    }

    public String getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(String penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public String getPostServiceFee() {
        return postServiceFee;
    }

    public void setPostServiceFee(String postServiceFee) {
        this.postServiceFee = postServiceFee;
    }

    public String getCompanyRealPay() {
        return companyRealPay;
    }

    public void setCompanyRealPay(String companyRealPay) {
        this.companyRealPay = companyRealPay;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCallUserEmployeeId() {
        return callUserEmployeeId;
    }

    public void setCallUserEmployeeId(String callUserEmployeeId) {
        this.callUserEmployeeId = callUserEmployeeId;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
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

    public String getJourneyNo() {
        return journeyNo;
    }

    public void setJourneyNo(String journeyNo) {
        this.journeyNo = journeyNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCallUserCompanyCode() {
        return callUserCompanyCode;
    }

    public void setCallUserCompanyCode(String callUserCompanyCode) {
        this.callUserCompanyCode = callUserCompanyCode;
    }

    public String getDepartmentName3() {
        return departmentName3;
    }

    public void setDepartmentName3(String departmentName3) {
        this.departmentName3 = departmentName3;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDefineValue1() {
        return defineValue1;
    }

    public void setDefineValue1(String defineValue1) {
        this.defineValue1 = defineValue1;
    }

    public String getDefineValue2() {
        return defineValue2;
    }

    public void setDefineValue2(String defineValue2) {
        this.defineValue2 = defineValue2;
    }

    public String getCallUserEmployeeIpin() {
        return callUserEmployeeIpin;
    }

    public void setCallUserEmployeeIpin(String callUserEmployeeIpin) {
        this.callUserEmployeeIpin = callUserEmployeeIpin;
    }

    public String getCallUserPhone() {
        return callUserPhone;
    }

    public void setCallUserPhone(String callUserPhone) {
        this.callUserPhone = callUserPhone;
    }

    public String getCarBasicFeeDetail() {
        return carBasicFeeDetail;
    }

    public void setCarBasicFeeDetail(String carBasicFeeDetail) {
        this.carBasicFeeDetail = carBasicFeeDetail;
    }

    public String getCarValueAddFeeDetail() {
        return carValueAddFeeDetail;
    }

    public void setCarValueAddFeeDetail(String carValueAddFeeDetail) {
        this.carValueAddFeeDetail = carValueAddFeeDetail;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getCallContry() {
        return callContry;
    }

    public void setCallContry(String callContry) {
        this.callContry = callContry;
    }

    public String getFixedLocationName() {
        return fixedLocationName;
    }

    public void setFixedLocationName(String fixedLocationName) {
        this.fixedLocationName = fixedLocationName;
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getEndorsementNo() {
        return endorsementNo;
    }

    public void setEndorsementNo(String endorsementNo) {
        this.endorsementNo = endorsementNo;
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

    public String getUseCarRemark() {
        return useCarRemark;
    }

    public void setUseCarRemark(String useCarRemark) {
        this.useCarRemark = useCarRemark;
    }

    public String getAuthorizeStatus() {
        return authorizeStatus;
    }

    public void setAuthorizeStatus(String authorizeStatus) {
        this.authorizeStatus = authorizeStatus;
    }

    public String getPersonalPayment() {
        return personalPayment;
    }

    public void setPersonalPayment(String personalPayment) {
        this.personalPayment = personalPayment;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRealStartPlace() {
        return realStartPlace;
    }

    public void setRealStartPlace(String realStartPlace) {
        this.realStartPlace = realStartPlace;
    }

    public String getRealDestination() {
        return realDestination;
    }

    public void setRealDestination(String realDestination) {
        this.realDestination = realDestination;
    }

    public String getVendorfeeOrgin() {
        return vendorfeeOrgin;
    }

    public void setVendorfeeOrgin(String vendorfeeOrgin) {
        this.vendorfeeOrgin = vendorfeeOrgin;
    }

    public String getNormalFee() {
        return normalFee;
    }

    public void setNormalFee(String normalFee) {
        this.normalFee = normalFee;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getCostCenter6() {
        return costCenter6;
    }

    public void setCostCenter6(String costCenter6) {
        this.costCenter6 = costCenter6;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDriveDistance() {
        return driveDistance;
    }

    public void setDriveDistance(String driveDistance) {
        this.driveDistance = driveDistance;
    }

    public String getRealDurationMinute() {
        return realDurationMinute;
    }

    public void setRealDurationMinute(String realDurationMinute) {
        this.realDurationMinute = realDurationMinute;
    }


    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public void setAdditionalRemarks(String additionalRemarks) {
        this.additionalRemarks = additionalRemarks;
    }

    public String getPersonFeeDetail() {
        return personFeeDetail;
    }

    public void setPersonFeeDetail(String personFeeDetail) {
        this.personFeeDetail = personFeeDetail;
    }

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
