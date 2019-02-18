package com.lnsoft.controller;

import com.lnsoft.model.TicketInfo;
import com.lnsoft.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Chr on 2019/2/11/0011.
 */
@RestController
public class TicketServerController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/buyTicket")
    public TicketInfo buyTicket(@RequestParam(required = false)String idcard){
        try {
            //模拟业务
            System.out.println("开始购票============");
            System.out.println("购票成功============");
            //返回车厢号，座位号，购买时间等等
            return ticketService.queryTicket(idcard);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
