package com.laoshiren.hello.rabbit.spring.rpc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.spring.rpc.consumer.QueueExchangeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.rpc.controller
 * ClassName:       ProviderController
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2021/5/24 16:36
 * Version:         1.0.0
 */
@RestController
@Slf4j
public class ProviderController {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/produce")
    private Object produce() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("code","2");
        Object o = rabbitTemplate.convertSendAndReceive(QueueExchangeBean.EXCHANGE_NAME,QueueExchangeBean.ROUTING_KEY_NAME, objectMapper.writeValueAsString(map));
        log.info("{}", o);
        return o;
    }

}
