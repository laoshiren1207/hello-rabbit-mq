package com.laoshiren.hello.rabbit.spring.publish.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.publish.consumer
 * ClassName:       FanoutConsumer
 * Author:          laoshiren
 * Description:     发布订阅者模式 <img src="https://www.rabbitmq.com/img/tutorials/python-three-overall.png" />
 * Date:            2020/10/6 22:42
 * Version:         1.0
 */
@Component
@Slf4j
public class FanoutConsumer {

    /*
     * 此处声明多个消费者
     */

    @RabbitListener(
            // 绑定
            bindings = {@QueueBinding(
                    // 创建临时队列
                    value = @Queue(""),
                    // 配置交换机
                    exchange = @Exchange(name = "spring-fanout-ex",type = ExchangeTypes.FANOUT))
            })
    public void receiveMessageFanout1(String message){
        log.info("fanout1 -- {}",message);
    }

    @RabbitListener(bindings = {@QueueBinding(
                    value = @Queue(""),
                    exchange = @Exchange(name = "spring-fanout-ex",type = ExchangeTypes.FANOUT))})
    public void receiveMessageFanout2(String message){
        log.info("fanout2 -- {}",message);
    }

}
