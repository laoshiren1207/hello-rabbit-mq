package com.laoshiren.hello.rabbit.spring.delay.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.ttl.queue
 * ClassName:       DelayQueueConfiguration
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/19 10:41
 * Version:         1.0.0
 */
@Slf4j
@Configuration
public class DelayQueueConfiguration implements DelayQueueConstant {

    @RabbitListener(queues= DelayQueueConstant.queueName)
    public void receiveMessageTopic2(String message) {
        log.info("topic delay {}", message);
    }

}
