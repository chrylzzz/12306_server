package com.lnsoft.service;

import com.lnsoft.model.TicketInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created By Chr on 2019/2/11/0011.
 */
@Service
public class TicketServiceImpl implements TicketService {

    TicketInfo ticketInfo = new TicketInfo();

    int i=0;
    @Override
    public TicketInfo queryTicket(String idcard) {
        ticketInfo.setId(idcard + "=="+i);
        ticketInfo.setBuyTime(new Date());
        ticketInfo.setStatus(0);
        ticketInfo.setTicketMoney(new BigDecimal(300));
        ticketInfo.setVersion("1");
        try {
            //模拟业务
            new Thread().sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ticketInfo;
    }
}
