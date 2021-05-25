package com.laoshiren.hello.rabbit.spring.delay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.commons.json.JsonUtils;
import com.laoshiren.hello.rabbit.spring.delay.queue.DelayQueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/delay/{routingKey}")
    public ResponseEntity<Map<String,String>> send( @PathVariable(name = "routingKey") String routingKey ){
        log.info(" - ");
        Map<String,String> map = new LinkedHashMap<>();
        map.put("delay","queue");
        String json = JsonUtils.obj2json(map);
        rabbitTemplate.convertAndSend(DelayQueueConstant.exchangeName,routingKey , json, messagePostProcessor->{
            messagePostProcessor.getMessageProperties()
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            messagePostProcessor.getMessageProperties()
                    .setDelay(5*1000);   // 毫秒为单位，指定此消息的延时时长
            return messagePostProcessor;
        });
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
