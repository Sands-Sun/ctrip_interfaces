package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
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

}
