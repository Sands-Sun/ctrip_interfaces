package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CarOrderSettlementBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "RecordId")
    private String recordId; //结算明细ID，与OrderType字段一起共同确保数据唯一性，且仅针对当前产品类型唯一

    @JSONField(name = "BatchNo")
    private String batchNo; //结算批次号

    @JSONField(name = "CreateTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //结算生成时间

    @JSONField(name = "DataChangeLastTime", format = "yyyy-MM-dd HH:mm:ss")
    private Date dataChangeLastTime;//结算最晚更新时间

    @JSONField(name = "ProductType")
    private Integer productType; //结算订单产品类型1:国内租车2:国内接送机3:国际接送机4:马上用车5:国内包车

    @JSONField(name = "OrderID")
    private String orderId; //订单号

    @JSONField(name = "SelType")
    private String selType; //结算类型(M：月结，N：现付)

    @JSONField(name = "DelType")
    private String delType; //明细类型(O:出票，R：退票)

    @JSONField(name = "CtripCardNo")
    private String ctripCardNo; //携程卡号

    @JSONField(name = "UID")
    private String uid; //携程卡号

    @JSONField(name = "Amount")
    private BigDecimal amount; //卖价

    @JSONField(name = "CarAddTaxAmount")
    private BigDecimal carAddTaxAmount; //手续费（用车加收税额）

    @JSONField(name = "CarBasicFeeDetail")
    private String carBasicFeeDetail; //用车基础费用明细

    @JSONField(name = "CarValueAddFee")
    private BigDecimal carValueAddFee; //增值费用

    @JSONField(name = "CarValueAddFeeDetail")
    private String carValueAddFeeDetail; //用车增值费用明细

    @JSONField(name = "penaltyFee")
    private BigDecimal penaltyFee; //违约金
    /**
     * 实收实付=
     *      Amount(卖价)+CarValueAddFee(增值费用)+
     *      PenaltyFee(违约金)+ServerFee(商旅管理服务费)+
     *      ExpressFee(快递费)
     * */
    @JSONField(name = "RealAmount")
    private BigDecimal realAmount;

    @JSONField(name = "ServerFee")
    private BigDecimal serverFee; //商旅管理服务费

    @JSONField(name = "ExpressFee")
    private BigDecimal expressFee;//快递费

    @JSONField(name = "OrderType")
    private Integer orderType; //订单类型9：月结用车；10：现付用车

    @JSONField(name = "SubAccCheckBatchNo")
    private String SubAccCheckBatchNo; //结算子批次号

    @JSONField(name = "TripID")
    private Long tripId; //行程号，又名“行程打包单号”

    @JSONField(name = "SettlementCurrency")
    private String settlementCurrency; //结算币种

    @JSONField(name = "PostServiceFee")
    private BigDecimal postServiceFee; //后收商旅管理服务费 后收商旅管理服务费：指预订过程中不展示，仅在结算数据中体现。
    /**
     * 实收实付(含后收商旅管理服务费)=
     *      Amount(卖价)+CarValueAddFee(增值费用)+
     *      CarAddTaxAmount(用车加收税额)+PenaltyFee(违约金)+
     *      ServerFee(商旅管理服务费)+ExpressFee(快递费)+
     *      PostServiceFee（后收商旅管理服务费）
     * */
    @JSONField(name = "RealAmountHasPost")
    private BigDecimal realAmountHasPost;

    @JSONField(name = "IsChecked")
    private Boolean checked;

    @JSONField(name = "InvoiceIds")
    private List<String> invoiceIds;

    @JSONField(name = "BatchNoStartDate")
    private String batchNoStartDate; //批次起始日期,yyyyMMdd

    @JSONField(name = "BatchNoEndDate")
    private String batchNoEndDate;  //批次结束日期,yyyyMMdd

    @JSONField(name = "CorpID")
    private String corpID;  //公司ID

    @JSONField(name = "AccountID")
    private String accountID; //主账户ID

    @JSONField(name = "Dept1")
    private String dept1;

    @JSONField(name = "Dept2")
    private String dept2;

    @JSONField(name = "Dept3")
    private String dept3;

    @JSONField(name = "Dept4")
    private String dept4;

    @JSONField(name = "Dept5")
    private String dept5;

    @JSONField(name = "Dept6")
    private String dept6;

    @JSONField(name = "Dept7")
    private String dept7;

    @JSONField(name = "Dept8")
    private String dept8;

    @JSONField(name = "Dept9")
    private String dept9;

    @JSONField(name = "Dept10")
    private String dept10;

    @JSONField(name = "LoanServiceFee")
    private BigDecimal loanServiceFee; //垫资服务费

    @JSONField(name = "PayMixFlag")
    private String payMixFlag; //是否为混付 T=混付，F=非混付（打车和接送机输出此字段，租车和包车不输出）

    @JSONField(name = "PersonAmount")
    private BigDecimal personAmount; //个人支付金额（打车和接送机输出此字段，租车和包车不输出）

    }
