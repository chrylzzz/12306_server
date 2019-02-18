package com.lnsoft.service;

import com.lnsoft.model.TicketInfo;

/**
 * Created By Chr on 2019/2/11/0011.
 */
public interface TicketService {
    TicketInfo queryTicket(String idcard);
}
