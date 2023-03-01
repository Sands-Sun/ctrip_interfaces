package com.dne.ctrip.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(Integer subAccountId) {
        this.subAccountId = subAccountId;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getCostCenter1() {
        return costCenter1;
    }

    public void setCostCenter1(String costCenter1) {
        this.costCenter1 = costCenter1;
    }

    public String getCostCenter2() {
        return costCenter2;
    }

    public void setCostCenter2(String costCenter2) {
        this.costCenter2 = costCenter2;
    }

    public String getCostCenter3() {
        return costCenter3;
    }

    public void setCostCenter3(String costCenter3) {
        this.costCenter3 = costCenter3;
    }

    public String getCostCenter4() {
        return costCenter4;
    }

    public void setCostCenter4(String costCenter4) {
        this.costCenter4 = costCenter4;
    }

    public String getCostCenter5() {
        return costCenter5;
    }

    public void setCostCenter5(String costCenter5) {
        this.costCenter5 = costCenter5;
    }

    public String getCostCenter6() {
        return costCenter6;
    }

    public void setCostCenter6(String costCenter6) {
        this.costCenter6 = costCenter6;
    }

    public String getJourneyReason() {
        return journeyReason;
    }

    public void setJourneyReason(String journeyReason) {
        this.journeyReason = journeyReason;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDefineValue() {
        return defineValue;
    }

    public void setDefineValue(String defineValue) {
        this.defineValue = defineValue;
    }

    public String getDefineValue2() {
        return defineValue2;
    }

    public void setDefineValue2(String defineValue2) {
        this.defineValue2 = defineValue2;
    }

    public String getJouneryId() {
        return jouneryId;
    }

    public void setJouneryId(String jouneryId) {
        this.jouneryId = jouneryId;
    }

    public String getRcCodeId() {
        return rcCodeId;
    }

    public void setRcCodeId(String rcCodeId) {
        this.rcCodeId = rcCodeId;
    }

    public String getRcCodeName() {
        return rcCodeName;
    }

    public void setRcCodeName(String rcCodeName) {
        this.rcCodeName = rcCodeName;
    }

    public String getTripBookPolicy() {
        return tripBookPolicy;
    }

    public void setTripBookPolicy(String tripBookPolicy) {
        this.tripBookPolicy = tripBookPolicy;
    }

    public String getTripPayPolicy() {
        return tripPayPolicy;
    }

    public void setTripPayPolicy(String tripPayPolicy) {
        this.tripPayPolicy = tripPayPolicy;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
