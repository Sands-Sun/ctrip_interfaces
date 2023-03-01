package com.dne.ctrip.finance.service;

import com.dne.ctrip.entity.DummyCostCenter;
import com.dne.ctrip.entity.StatementInfo;
import com.dne.ctrip.finance.dao.OrderCheckStatementDao;
import com.dne.ctrip.finance.entity.OrderCheckInfo;
import com.dne.ctrip.finance.entity.OrderCheckResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderCheckStatementService {

    @Autowired
    private OrderCheckStatementDao orderCheckStatementDao;

    /**
     * 保存对账单
     * @param statement
     * @return
     */
    public int addCheckStatement(StatementInfo statement) {
        return orderCheckStatementDao.addCheckStatement(statement);
    }

    /**
     * 修改对账单
     * @param statement
     */
    public void updCheckStatement(StatementInfo statement) {
        orderCheckStatementDao.updCheckStatement(statement);
    }

    /**
     * 保存返款-月账单
     * @param paramMap
     * @return
     */
    public int addRefundStatements(Map<String, Object> paramMap) {
        return orderCheckStatementDao.addRefundStatements(paramMap);
    }


    /**
     * 保存订单-月对账单
     * @param paramMap
     * @return
     */
    public int addOrderStatements(Map<String, Object> paramMap) {
        return orderCheckStatementDao.addOrderStatements(paramMap);
    }

    /**
     * 更新对账结果单一
     * @param detail
     */
    public void updateCheckResultBySimple(OrderCheckResultDetail detail) {
        orderCheckStatementDao.updateCheckResultBySimple(detail);
    }


    /**
     * 更新对账结果
     * @param orderList
     */
    public void updCheckStatementResult(List<OrderCheckInfo> orderList) {
        orderCheckStatementDao.updCheckStatementResult(orderList);
    }


    /**
     * 查询不存在的订单
     * @param batchNo
     * @return
     */
    public List<OrderCheckResultDetail> getCheckNoExistOrders(String batchNo) {
        return orderCheckStatementDao.getCheckNoExistOrders(batchNo);
    }

    /**
     * 查询重复的订单
     * @param batchNo
     * @return
     */
    public List<OrderCheckResultDetail> getCheckRepeatOrders(String batchNo) {
        return orderCheckStatementDao.getCheckRepeatOrders(batchNo);
    }

    /**
     * 查询金额大的订单
     * @param batchNo
     * @return
     */
    public List<OrderCheckResultDetail> getCheckLargeAmtOrders(String batchNo) {
        return orderCheckStatementDao.getCheckLargeAmtOrders(batchNo);
    }

    public List<DummyCostCenter> getDummyCostCenters() {
        return orderCheckStatementDao.getDummyCostCenters();
    }
}
