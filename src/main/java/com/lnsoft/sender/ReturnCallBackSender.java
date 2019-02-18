package com.lnsoft.sender;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Created By Chr on 2019/2/17/0017.
 */
@Component
public class ReturnCallBackSender implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message,//
                                int replyCode,//
                                String replyText,//
                                String exchange,//
                                String routingKey) {
        System.out.println("return--message:" + new String(message.getBody()) +//
                            ",replyCode:" + replyCode +//
                            ",replyText:" + replyText +//
                            ",exchange:" + exchange +//
                            ",routingKey:" + routingKey);

        //消息发送失败的情况：
//        如果消息没有到exchange,则confirm回调,ack=false

//        如果消息到达exchange,则confirm回调,ack=true

//        exchange到queue成功,则不回调return

//        exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
    }
}
