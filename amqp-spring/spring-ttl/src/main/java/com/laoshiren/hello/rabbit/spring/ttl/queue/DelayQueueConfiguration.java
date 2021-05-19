package com.laoshiren.hello.rabbit.spring.ttl.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
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
