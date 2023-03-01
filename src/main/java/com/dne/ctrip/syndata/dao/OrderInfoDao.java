package com.dne.ctrip.syndata.dao;

import com.dne.ctrip.entity.OrderInfo;
import com.dne.ctrip.entity.OrderReportInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfoDao {

    List<OrderReportInfo> getOrderReportList(Map<String, Object> paramMap);
    List<OrderInfo> getUpdSyncOrderInfo(String batchNo);

    void addSyncOrder(@Param("items") List<OrderInfo> orderList);

    int addOrderInfo(String batchNo);

    void updOrderInfo(Map<String, Object> paramMap);
}
