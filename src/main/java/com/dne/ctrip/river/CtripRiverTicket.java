package com.dne.ctrip.river;

import com.alibaba.fastjson.annotation.JSONField;
import com.dne.ctrip.domain.ResponseStatus;

import java.io.Serializable;

public class CtripRiverTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "Ticket")
    private String ticket;

    @JSONField(name = "Status")
    private ResponseStatus status;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
