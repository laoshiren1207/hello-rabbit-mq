package com.laoshiren.hello.rabbit.spring.rpc.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.rpc.consumer
 * ClassName:       QueueExchangeBean
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/24 16:30
 * Version:         1.0.0
 */
@Configuration
public class QueueExchangeBean {

    public final static String QUEUE_NAME = "spring-rpc-queue";
    public final static String EXCHANGE_NAME = "spring.rpc";
    public final static String ROUTING_KEY_NAME = "rpc";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(DirectExchange exchange,
                           Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY_NAME);
    }

}
