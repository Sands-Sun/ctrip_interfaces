package com.dne.ctrip.finance.dao;

import com.dne.ctrip.entity.DummyCostCenter;
import com.dne.ctrip.entity.StatementInfo;
import com.dne.ctrip.finance.entity.OrderCheckInfo;
import com.dne.ctrip.finance.entity.OrderCheckResultDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderCheckStatementDao {


    /**
     * 保存对账单
     * @param statement
     * @return
     */
    int addCheckStatement(StatementInfo statement);

    /**
     * 修改对账单
     * @param statement
     */
    void updCheckStatement(StatementInfo statement);

    /**
     * 保存返款-月账单
     * @param paramMap
     * @return
     */
   int addRefundStatements(Map<String, Object> paramMap);


    /**
     * 保存返款-月账单
     * @param paramMap
     * @return
     */
    int addOrderStatements(Map<String, Object> paramMap);



    /**
     * 查询不存在的订单
     * @param batchNo
     * @return
     */
     List<OrderCheckResultDetail> getCheckNoExistOrders(String batchNo);

    /**
     * 查询重复的订单
     * @param batchNo
     * @return
     */
     List<OrderCheckResultDetail> getCheckRepeatOrders(String batchNo);

    /**
     * 查询金额大的订单
     * @param batchNo
     * @return
     */
     List<OrderCheckResultDetail> getCheckLargeAmtOrders(String batchNo);


    /**
     * 更新对账结果单一
     * @param detail
     */
    void updateCheckResultBySimple(OrderCheckResultDetail detail);

    /**
     * 更新对账结果
     * @param orderList
     */
    void updCheckStatementResult(@Param("items") List<OrderCheckInfo> orderList);


    List<DummyCostCenter> getDummyCostCenters();
}
