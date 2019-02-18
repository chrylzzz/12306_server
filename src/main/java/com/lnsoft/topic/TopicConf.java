package com.lnsoft.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By Chr on 2019/2/12/0012.
 */
@Configuration
public class TopicConf {

    //队列:消费者响应到服务端队列：该队类为消费者把数据拿到之后，返回给服务端的队列
    @Bean(name = "orderMessage")//队列名字
    public Queue queueMessage() {
        //系统启动时，创建的order队列到rabbitMQ
        return new Queue("topic.ticketRespInfo");
    }

    @Bean//交换器//名字为exchange需要在rabbitmq服务器添加交换机名字为exchange
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    //系统启动时：将exchange的交换器与队列绑定
    //将队列orderMessage与exchange绑定，binding_key为topic.ticketRespInfo，完全匹配

    //绑定，声明关系
    @Bean
    Binding bindingExchangeMessage(@Qualifier("orderMessage") Queue queueMessage, TopicExchange exchange) {
        //将交换器和指定的队列 通过路由键 绑定起来
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.ticketRespInfo");
    }
}
