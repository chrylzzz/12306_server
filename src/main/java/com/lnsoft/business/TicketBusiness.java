package com.lnsoft.business;

import com.lnsoft.model.TicketInfo;
import com.lnsoft.sender.RabbitSender;
import com.lnsoft.service.TicketService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 需要消息确认回执，和消息失败重发：
 * 处理响应结果，如果有订单的，OrderBusiness
 * 如果有用户的，UserBusiness
 * Created By Chr on 2019/2/12/0012.
 */
@Component
public class TicketBusiness {

    @Autowired
    private TicketService ticketService;//业务

    @Autowired
    private RabbitSender rabbitSender;//待处理的结果

    private int count = 0;

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 监听指定的topic.idcard队列，档次队列有数据时，数据就会被取走
     * <p>
     * 该监听的作用，消费者下单的信息放入到topic.ticket队列中，服务器去该队列拿取下单信息，从而进行出票，出票成功后
     * 通过send方法，将出票信息放入到topic.ticketRespInfo路由键的队列中，由消费者去拉取数据，从而获得出票信息
     *
     * @param idcard ：身份证号
     */
    @RabbitListener(queues = "topic.ticket")//监听的队列，监听客户端的队列，等待拉取客户端购票请求
    @RabbitHandler
    public void process(String idcard, Message message, Channel channel) throws IOException {
        try {

            //###############################################
            //模拟消费者下单之后订单放到队列topic.ticket中，客户端去队列拉取数据，进行业务操作
            System.out.println("==="+idcard + "===用户购票请求，用户购票，正在下单，服务器调用订单系统。。。。，下单成功，正在进行出票===出票成功！！~");
            TicketInfo result = ticketService.queryTicket(idcard);
            //把服务端处理的情况再塞入到服务端队列，等待消费者拉取，（把在服务器查到的结果数据（第二个参数），放到队列中（第一个参数），供消费者拿取）
            rabbitSender.send("topic.ticketRespInfo", "==="+result.getId() + "===" + format.format(new Date()));
            //###############################################


            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // false只确认当前一个消息收到，true确认所有consumer获得的消息
            //true为确认所有的消费者都收到消息后，消息再删除
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();

            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
            } else {//消息消费失败，消息返回queue
                System.out.println("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列
            }

        }
    }


}
