package com.dne.ctrip.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String orderId;// 订单编号
    private Date createTime;// 叫单时间
    private Integer useCarType;// 用车方式
    private String requireLevel;// 车型
    private String city;// 城市ID
    private String cityName;// 城市名称
    private String startName;// 出发地地址
    private String endName;// 目的地地址
    private Date departureTime;// 出发时间
    private Date finishTime;// 结束计价时间
    private String status;// 订单状态
    private String payType;// PaymentType	支付方式（ACCNT:公司账户CCARD:信用卡CASH:现付）
    private String memberId;// 员工在携程企业的ID CtripCardNo
    private Date payTime;// 支付时间
    private String normalDistance;// 总里程
    private BigDecimal totalPrice;// 总金额  SettlementBaseInfo.RealAmount
    private BigDecimal actualPrice;// 实付金额（总金额-券抵扣金额） SettlementBaseInfo.RealAmountHasPost
    private BigDecimal servicesFee;  //商旅管理服务费
    private BigDecimal postServiceFree; //后收商旅管理服务费 后收商旅管理服务费：指预订过程中不展示，仅在结算数据中体现。
    private BigDecimal expressFee;  //快递费
    private BigDecimal carValueAddFee; //增值费用
    private BigDecimal carAddTaxAmount; //手续费（用车加收税额）
    private BigDecimal penaltyFee; //违约金
    private BigDecimal companyPay;// 公司支付金额 OrderDetail.OrderBaseInfo.AccntAmount
    private BigDecimal personalPay;// 个人支付金额 OrderDetail.OrderBaseInfo.PersonAmount
    private Boolean isInvoice;// 报销开票状态  SettlementBaseInfo.IsChecked
    private String callPhone;// 叫车人手机号
    private String passengerPhone;// 乘车人手机号
    private Date firstCreateDate;
    private Date updateDate;// 修改日期
    private String delFlag;// N正常,Y删除
    private String batchNo;// 同步批次号
    private String remark;//打车备注
    private String companyCode;//公司编码
    private String costCenter;//成本中心
    private String delType; //明细类型(O:出票，R：退票)



}
