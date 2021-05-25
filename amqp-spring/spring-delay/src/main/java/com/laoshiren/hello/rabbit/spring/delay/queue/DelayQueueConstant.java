package com.laoshiren.hello.rabbit.spring.delay.queue;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.ttl.queue
 * ClassName:       DelayQueueConstant
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/19 10:42
 * Version:         1.0.0
 */
public interface DelayQueueConstant {

    String queueName = "delay_queue_name";

    String exchangeName = "delay_exchange_name";

    String routingKey = "ttl";

}
