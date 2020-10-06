package com.laoshiren.hello.rabbit.spring.work.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.work.consumer
 * ClassName:       WorkConsumer
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 22:15
 * Version:         1.0
 */
@Component
@Slf4j
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = {@Queue("spring-work-queue")})
    public void receiveMessageWork1(String message){
        log.info("work1 {}",message);
    }

    @RabbitListener(queuesToDeclare = {@Queue("spring-work-queue")})
    public void receiveMessageWork2(String message){
        log.info("work2 {}",message);
    }

}
