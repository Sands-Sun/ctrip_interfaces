package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CarOrderBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "OrderID")
    private String orderId;

    @JSONField(name = "CarControlItems")
    private Integer carControlItems;

    @JSONField(name = "ChannelType")
    private Integer channelType; // 渠道号

    @JSONField(name = "ContactEmail")
    private String contactEmail; //联系人邮箱

    @JSONField(name = "ContactMobile")
    private String contactMobile;//联系人手机号

    @JSONField(name = "ContactName")
    private String contactName;  //联系人姓名

    @JSONField(name = "CorpTravelEndorsementId")
    private String corpTravelEndorsementId; //出差申请单

    @JSONField(name = "DealAmount")
    private BigDecimal dealAmount; //实际金额

    @JSONField(name = "FeeType")
    private String feeType; //费用类型C:因公P:因私

    @JSONField(name = "LanguageCode")
    private String languageCode; //语言版本(ZH-CN:中文，ZH-HK:繁体，EN-US：英文)

    @JSONField(name = "NeedInvoice")
    private String needInvoice; //是否需要发票

    @JSONField(name = "OrderAmount")
    private String orderAmount; //订单金额

    @JSONField(name = "OrderDate", format = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate; //预定时间
    /**
     * 马上叫车：
     * WaitReply：等待应答，WaitService：等待接驾，DriverArrived：司机已到达，
     * InService:正在服务，EndService：行程结束，Canceling：取消中,，
     * Canceled：已取消，Successful：已成交
     * */
    @JSONField(name = "OrderStatus")
    private String orderStatus;

    @JSONField(name = "OrderType")
    private Integer orderType;              //订单类型（1:国内接送机2:国际接送机3:国内包车4:国内租车6:随叫随到）

    @JSONField(name = "PaymentStatus")
    private String paymentStatus;           //取消标志位(T/F)支付状态（支付中：PP支付失败：PF支付成功：PS）

    @JSONField(name = "PaymentType")
    private String paymentType;             //支付方式（ACCNT:公司账户CCARD:信用卡CASH:现付）

    @JSONField(name = "PolicyID")
    private String policyID;                //政策执行人

    @JSONField(name = "ReachCarControl")
    private String reachCarControl;         //是否符合差标,1:符合,0:不符合,空:不管控

    @JSONField(name = "ReachTravel")
    private String reachTravel;             //是否符合出差申请

    @JSONField(name = "ServerFrom")
    private String serverFrom;              //订单来源

    @JSONField(name = "ServiceFee")
    private BigDecimal serviceFee;          //商旅管理服务费

    @JSONField(name = "TripID")
    private Long tripId;                    //行程号

    @JSONField(name = "Uid")
    private String uid;                     //携程卡号

    @JSONField(name = "UserName")
    private String userName;                //持卡人姓名

    @JSONField(name = "PreEmployeeID")
    private String preEmployeeId;           //持卡人编号

    @JSONField(name = "EndChargeAmount")
    private Double endChargeAmount;         //二次支付费用

    @JSONField(name = "RankName")
    private String rankName;                //预订人职级

    @JSONField(name = "RankNameEn")
    private String rankNameEn;              //预订人职级英文名

    @JSONField(name = "ConfirmPerson")
    private String confirmPerson;           //授权人

    @JSONField(name = "ConfirmPersonEID")
    private String confirmPersonEID;        //授权人员工编号

    @JSONField(name = "ConfirmPersonName")
    private String confirmPersonName;       //授权人姓名

    @JSONField(name = "AccntAmount")
    private BigDecimal accntAmount;         //公司支付金额此金额不包含后收商旅管理费，下单后不做金额更新。

    @JSONField(name = "PersonAmount")
    private BigDecimal personAmount;            //个人支付金额，下单后不做金额更新。

    @JSONField(name = "HasMixedPay")
    private Boolean hasMixedPay;            //是否是混付  true:有  false:无

}
