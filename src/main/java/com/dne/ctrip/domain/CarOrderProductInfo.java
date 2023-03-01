package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class CarOrderProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "DepartureCityId")
    private String departureCityId;			//	出发城市ID
    @JSONField(name = "DepartureCityName")
    private String departureCityName;		//	出发城市名
    @JSONField(name = "ArrivalCityId")
    private String arrivalCityId;			//	到达城市ID
    @JSONField(name = "ArrivalCityName")
    private String arrivalCityName;			//	到达城市名
    @JSONField(name = "VehicleName")
    private String vehicleName;				//	车型名称 经济型，舒适型，豪华型，商务型，出租车，优享型
    @JSONField(name = "VendorName")
    private String vendorName;				//	供应商名称

    public String getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(String departureCityId) {
        this.departureCityId = departureCityId;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getArrivalCityId() {
        return arrivalCityId;
    }

    public void setArrivalCityId(String arrivalCityId) {
        this.arrivalCityId = arrivalCityId;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
