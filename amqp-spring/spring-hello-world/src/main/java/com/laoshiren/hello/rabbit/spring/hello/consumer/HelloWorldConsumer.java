package com.laoshiren.hello.rabbit.spring.hello.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.hello.consumer
 * ClassName:       HelloWorldConsumer
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 16:29
 * Version:         1.0
 */
@Component
// 消费者监听
// queuesToDeclare 定义队列
// @Queue 队列
// 默认创建的就是 持久化非独占的队列
@RabbitListener(queuesToDeclare = {@Queue(name = "amqp-spring-hello",durable = "true")})
@Slf4j
public class HelloWorldConsumer {

    /*
     * 可以定义任意方法
     */

    /**
     * 接收消息的方法
     * @Annotation @RabbitHandler 表示mq消费者消费方法
     * @param message   消息体
     */
    @RabbitHandler
    public void receiveMessage(String message){
        log.info("queue: amqp-spring-hello  message {}",message);
    }

}
