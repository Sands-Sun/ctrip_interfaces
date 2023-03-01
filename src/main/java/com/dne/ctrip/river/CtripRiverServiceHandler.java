package com.dne.ctrip.river;

import com.alibaba.fastjson.JSONObject;
import com.dne.core.util.FileUtils;
import com.dne.core.util.HttpUtils;
import com.dne.core.util.StringUtils;
import com.dne.ctrip.domain.CarOrderSettlementSearchResponse;
import com.dne.ctrip.domain.ResponseStatusList;
import com.dne.ctrip.param.AuthParam;
import com.dne.ctrip.param.BaseRequestParam;
import com.dne.ctrip.param.CarOrderSettlementSearchRequest;
import com.dne.ctrip.param.CorpCustomInfoInfo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;

@Component
public class CtripRiverServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(CtripRiverServiceHandler.class);

    private static final CtripRiverTicket ticket = new CtripRiverTicket();

    private static Long ticketTime;

    @Autowired
    private CtripRiverDataConfig config;


    @PostConstruct
    public void init() {
        CtripRiverTicket hisTicket = (CtripRiverTicket) FileUtils.getObjFromFile(config.getTicketFile());
        log.info("River init history access ticket: " + hisTicket);
        if (null != hisTicket) {
            ticket.setTicket(hisTicket.getTicket());
        }
    }

    public ResponseStatusList batchSaveEmployeeInfo(CorpCustomInfoInfo custom){
        return postRiver(config.getEmployeeAddUrl(), custom, ResponseStatusList.class);
    }

    public CarOrderSettlementSearchResponse getOrderCarList(CarOrderSettlementSearchRequest param) {
        return postRiver(config.getOrderCarGetUrl(), param, CarOrderSettlementSearchResponse.class);
    }

    private <T> T postRiver(String url, AuthParam authParam, Class<T> respClass) {
        this.setAuth(authParam);
        String reqJson =  JSONObject.toJSONString(authParam);
        log.info("======>post-param:"+reqJson);
        String result = HttpUtils.doPost(url, reqJson);
        log.info("======>River post result: " + result);
        return JSONObject.parseObject(result, respClass);
    }

    private <T> T postRiver(String url, BaseRequestParam param, Class<T> respClass) {
        param.setAuth(Objects.isNull(param.getAuth()) ? new AuthParam() : param.getAuth());
        this.setAuth(param.getAuth());
        String reqJson =  JSONObject.toJSONString(param);
        log.info("======>post-param:"+reqJson);
        String result = HttpUtils.doPost(url, reqJson);
        log.info("======>River post result: " + result);
        return JSONObject.parseObject(result, respClass);
    }

    /**
     * 构造公共请求对象
     *
     * @return
     */
    private void setAuth(AuthParam authParam) {
        resetAccessTicket();
        authParam.setTicket(ticket.getTicket());
        authParam.setAppKey(config.getAppKey());
        authParam.setCorporationId(config.getCorporationId());
        authParam.setAppSecurity(config.getAppSecurity());
    }


    /**
     * 重置Ticket
     *
     */
    private void resetAccessTicket() {
        if (StringUtils.isEmpty(ticket.getTicket())) {
            this.refreshAccessTicket();
        } else {
            if (ticketTime == null || (System.currentTimeMillis() - ticketTime) / 1000 >= 7200) {
                this.refreshAccessTicket();
            }
        }
    }


    /**
     * 刷新Ticket
     */
    public void refreshAccessTicket() {
        CtripRiverTicket ctripRiverTicket = this.getRiverAccessTicket();
        if(ctripRiverTicket.getStatus() != null
                && ctripRiverTicket.getStatus().getSuccess().equals(true)){
            ticketTime = System.currentTimeMillis();
            ticket.setTicket(ctripRiverTicket.getTicket());
            FileUtils.saveObjToFile(ticket, config.getTicketFile());
        }else {
            throw new RuntimeException(ctripRiverTicket.toString());
        }
    }

    /**
     * 从DIDI查询Access Token
     *
     * @return
     */
    public CtripRiverTicket getRiverAccessTicket() {
        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("appKey", config.getAppKey());
        reqMap.put("appSecurity", config.getAppSecurity());
        reqMap.put("timestamp", System.currentTimeMillis());
        String respJson = HttpUtils.doPost(config.getAuthTicketUrl(), reqMap);
        log.info("Get access ticket result: " + respJson);
        return JSONObject.parseObject(respJson, CtripRiverTicket.class);
    }
}
