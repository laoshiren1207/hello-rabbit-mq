package com.laoshiren.hello.rabbit.spring.ttl.queue;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
    FanoutExchange delayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(DelayQueueConstant.exchangeName, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    // 绑定延时队列与交换机
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(delayPayQueue()).to(delayExchange());
    }

}
