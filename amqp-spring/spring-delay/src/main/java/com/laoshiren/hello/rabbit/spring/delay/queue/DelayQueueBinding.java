package com.laoshiren.hello.rabbit.spring.delay.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.ttl.queue
 * ClassName:       DelayQueueBinding
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/19 10:59
 * Version:         1.0.0
 */
@Configuration
public class DelayQueueBinding {

    @Bean
    public Queue delayPayQueue() {
        return new Queue(DelayQueueConstant.queueName, true);
    }

    // 定义广播模式的延时交换机 无需绑定路由
    @Bean
    public DirectExchange delayExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        DirectExchange directExchange = new DirectExchange(DelayQueueConstant.exchangeName, true, false, args);
        directExchange.setDelayed(true);
        return directExchange;
    }

    // 绑定延时队列与交换机
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(delayPayQueue()).to(delayExchange()).with("ttl");
    }

}
