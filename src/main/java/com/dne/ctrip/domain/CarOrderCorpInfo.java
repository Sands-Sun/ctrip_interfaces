package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarOrderCorpInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "CorpId")
    private String corpId;				    //公司编号

    @JSONField(name = "CorpName")
    private String corpName;				//	公司名称

    @JSONField(name = "AccountId")
    private Integer accountId;			    //	主账户ID

    @JSONField(name = "SubAccountId")
    private Integer subAccountId;			//	子账户ID

    @JSONField(name = "BookType")
    private String bookType;				//	预定方式（J：按行程预定O:按订单预定）

    @JSONField(name = "CostCenter1")
    private String costCenter1;			    //	成本中心一

    @JSONField(name = "CostCenter2")
    private String costCenter2;			    //	成本中心二

    @JSONField(name = "CostCenter3")
    private String costCenter3;			    //	成本中心三

    @JSONField(name = "CostCenter4")
    private String costCenter4;			    //	成本中心四

    @JSONField(name = "CostCenter5")
    private String costCenter5;			    //	成本中心五

    @JSONField(name = "CostCenter6")
    private String costCenter6;			    //	成本中心六

    @JSONField(name = "JourneyReason")
    private String journeyReason;		    //	出行目的

    @JSONField(name = "Project")
    private String project;				    //	项目号

    @JSONField(name = "DefineValue")
    private String defineValue;			    //	自定义字段一

    @JSONField(name = "DefineValue2")
    private String defineValue2;			//	自定义字段二

    @JSONField(name = "JouneryId")
    private String jouneryId;			    //	关联行程号

    @JSONField(name = "RcCodeId")
    private String rcCodeId;				//编号ID 注：如果存在多个Code用','隔开

    @JSONField(name = "RcCodeName")
    private String rcCodeName;			    //ReasonCode名称 （例如：随同上级领导出差）

    @JSONField(name = "TripBookPolicy")
    private String tripBookPolicy;		    //	行程预订政策(P:使用政策执行人政策C:使用预订卡政策)

    @JSONField(name = "TripPayPolicy")
    private String tripPayPolicy;		    //	行程支付人开关(P:政策执行人结算C:预订卡结算)

    @JSONField(name = "ProjectCode")
    private String projectCode;			    //项目编码
}
