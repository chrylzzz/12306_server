package com.lnsoft.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created By Chr on 2019/2/12/0012.
 */
@Component
public class RabbitSender {
    @Autowired
    private AmqpTemplate template;

    //服务端发送消息到路由key所绑定的exchange和队列中
    public void send(String routingKey,String orderInfo){
        template.convertAndSend(routingKey,orderInfo);
    }
}
