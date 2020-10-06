package com.laoshiren.hello.rabbit.spring.topic.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.topic.consumer
 * ClassName:       TopicConsumer
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 23:38
 * Version:         1.0
 */
@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(""),
                    // 设置topic 类型的交换机
                    exchange = @Exchange(name = "spring-topic-ex",type = ExchangeTypes.TOPIC),
                    key = {"rabbit.#"}
            )
    })
    public void receiveMessageTopic1(String message){
        log.info("topic 1 {}",message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(""),
                    exchange = @Exchange(name = "spring-topic-ex",type = ExchangeTypes.TOPIC),
                    key = {"*.laoshiren.*","#.1207"}
            )
    })
    public void receiveMessageTopic2(String message){
        log.info("topic 2 {}",message);
    }

}
