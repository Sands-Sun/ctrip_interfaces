package com.dne.ctrip.syndata.service;

import com.dne.ctrip.entity.OrderInfo;
import com.dne.ctrip.entity.OrderReportInfo;
import com.dne.ctrip.syndata.dao.OrderInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    public List<OrderReportInfo> getOrderReportList(Map<String, Object> paramMap){
        return orderInfoDao.getOrderReportList(paramMap);
    }

    public List<OrderInfo> getUpdSyncOrderInfo(String batchNo) {
        return orderInfoDao.getUpdSyncOrderInfo(batchNo);
    }

    public void addSyncOrder(List<OrderInfo> orderList) {
        orderInfoDao.addSyncOrder(orderList);
    }

    public int addOrderInfo(String batchNo) {
        return orderInfoDao.addOrderInfo(batchNo);
    }

    public void updOrderInfo(Map<String, Object> paramMap) {
        orderInfoDao.updOrderInfo(paramMap);
    }
}
