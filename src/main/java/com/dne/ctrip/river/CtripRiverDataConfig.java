package com.dne.ctrip.river;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CtripRiverDataConfig {

    @Value("${ctrip.access.ticket.file}")
    private String ticketFile;// 访问令牌存放文件

    @Value("${ctrip.app.key}")
    private String appKey;// 申请应用时分配的AppKey

    @Value("${ctrip.app.security}")
    private String appSecurity;// 申请应用时分配的AppSecret

    @Value("${ctrip.corporation.id}")
    private String corporationId;

    @Value("${ctrip.auth.ticket.url}")
    private String authTicketUrl;// 获取授权access_token Url

    @Value("${ctrip.employee.add}")
    private String employeeAddUrl;// 用户 新增Url

    @Value("${ctrip.order.car.get}")
    private String orderCarGetUrl;//  订单查询Url

    public String getTicketFile() {
        return ticketFile;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecurity() {
        return appSecurity;
    }

    public String getCorporationId() {
        return corporationId;
    }

    public String getAuthTicketUrl() {
        return authTicketUrl;
    }

    public String getEmployeeAddUrl() {
        return employeeAddUrl;
    }

    public String getOrderCarGetUrl() {
        return orderCarGetUrl;
    }
}
