package com.laoshiren.hello.rabbit.spring.rpc.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.rpc.consumer
 * ClassName:       MessageConsumer
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/24 16:24
 * Version:         1.0.0
 */
@Slf4j
@Configuration
public class MessageConsumer {

    @Resource
    private ObjectMapper objectMapper;

    @RabbitListener(queues = QueueExchangeBean.QUEUE_NAME)
    private String receiveMessage(String message) throws JsonProcessingException, InterruptedException {
        log.info(message);
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");
        result.put("message", "操作成功");
        Thread.sleep(1000);
        return objectMapper.writeValueAsString(result);
    }

}
