package com.lnsoft.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created By Chr on 2019/2/11/0011.
 */
public class TicketInfo {
    private String id;
    private Date buyTime;
    private BigDecimal ticketMoney;
    private Integer status;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getTicketMoney() {
        return ticketMoney;
    }

    public void setTicketMoney(BigDecimal ticketMoney) {
        this.ticketMoney = ticketMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
