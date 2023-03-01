package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarOrderQuickProductInfo extends CarOrderProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "BookingType")
    private String bookingType;				//	预订类型 1:实时单 2:预约单
    @JSONField(name = "UseTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date useTime;					//	用车时间
    @JSONField(name = "DepartureAddressDetail")
    private String departureAddressDetail;	//	出发详细地址
    @JSONField(name = "ArrivalAddressDetail")
    private String arrivalAddressDetail;	//	到达详细地址
    @JSONField(name = "VehicleId")
    private String vehicleId;				//	车型Id,1:经济型，2:舒适型，3:豪华型，4:商务型，17：出租车，1900：优享型
    @JSONField(name = "VehicleName")
    private String vehicleName;				//	车型名称, 经济型，舒适型，豪华型，商务型，出租车，优享型
    @JSONField(name = "NormalDistance")
    private String normalDistance;			//	实际行驶距离
    @JSONField(name = "NormalTime")
    private String normalTime;				//	实际行驶时长
    @JSONField(name = "ServiceBeginTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date serviceBeginTime;		//	服务开始时间
    @JSONField(name = "ServiceEndTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date serviceEndTime;			//	服务结束时间
    @JSONField(name = "SceneName")
    private String sceneName;				//	场景名称
    @JSONField(name = "StandardName")
    private String standardName;			//	差标名称
    @JSONField(name = "DestinationChangeList")
    private List<DestinationChangeInfoApi> destinationChangeList;	//目的地变更信息 按修改时间升序排列

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getDepartureAddressDetail() {
        return departureAddressDetail;
    }

    public void setDepartureAddressDetail(String departureAddressDetail) {
        this.departureAddressDetail = departureAddressDetail;
    }

    public String getArrivalAddressDetail() {
        return arrivalAddressDetail;
    }

    public void setArrivalAddressDetail(String arrivalAddressDetail) {
        this.arrivalAddressDetail = arrivalAddressDetail;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String getVehicleName() {
        return vehicleName;
    }

    @Override
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getNormalDistance() {
        return normalDistance;
    }

    public void setNormalDistance(String normalDistance) {
        this.normalDistance = normalDistance;
    }

    public String getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(String normalTime) {
        this.normalTime = normalTime;
    }

    public Date getServiceBeginTime() {
        return serviceBeginTime;
    }

    public void setServiceBeginTime(Date serviceBeginTime) {
        this.serviceBeginTime = serviceBeginTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public List<DestinationChangeInfoApi> getDestinationChangeList() {
        return destinationChangeList;
    }

    public void setDestinationChangeList(List<DestinationChangeInfoApi> destinationChangeList) {
        this.destinationChangeList = destinationChangeList;
    }
}
