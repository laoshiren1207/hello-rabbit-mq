package com.laoshiren.hello.rabbit.spring.ttl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.spring.ttl.queue.DelayQueueConstant;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.ttl.controller
 * ClassName:       QueueController
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/19 10:44
 * Version:         1.0.0
 */
@RestController
@Slf4j
public class QueueController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/delay")
    public ResponseEntity<Map<String,String>> send() throws JsonProcessingException {
        log.info(" - ");
        Map<String,String> map = new LinkedHashMap<>();
        map.put("delay","queue");
        String json = objectMapper.writeValueAsString(map);
        rabbitTemplate.convertAndSend(DelayQueueConstant.exchangeName, DelayQueueConstant.routingKey, json, messagePostProcessor->{
            messagePostProcessor.getMessageProperties()
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            messagePostProcessor.getMessageProperties()
                    .setDelay(5*1000);   // 毫秒为单位，指定此消息的延时时长
            return messagePostProcessor;
        });
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
