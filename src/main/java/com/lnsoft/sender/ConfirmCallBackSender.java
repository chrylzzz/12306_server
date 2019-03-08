package com.lnsoft.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * //后来加上的
 * Created By Chr on 2019/2/17/0017.
 */
@Component
public class ConfirmCallBackSender implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {//ack为true为消息成功
            System.out.println("消息确认成功cause" + cause);
            //消息成功后确认
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            //处理丢失的消息
            System.out.println("消息确认失败:" + correlationData.getId() + "#cause" + cause);
            //重新扔回队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }

    }
}
