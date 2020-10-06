package com.laoshiren.hello.rabbit.spring.routing.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.routing.consumer
 * ClassName:       RoutingConsumer
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 23:08
 * Version:         1.0
 */
@Component
@Slf4j
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    // 临时队列
                    value = @Queue(),
                    // 交换机 type 默认就是direct直连模式
                    exchange = @Exchange(name = "spring-routing-ex",type = ExchangeTypes.DIRECT),
                    // 路由Key,可以声明多个
                    key = {"laoshiren"}
            )
    })
    public void receiveMessageRouting1(String message){
        log.info("routing 1 {}",message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "spring-routing-ex",type = ExchangeTypes.DIRECT),
                    key = {"laoshiren","rabbit"}
            )
    })
    public void receiveMessageRouting2(String message){
        log.info("routing 2 {}",message);
    }

}
