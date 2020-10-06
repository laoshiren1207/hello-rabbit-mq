package com.laoshiren.hello.rabbit.spring.routing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.routing
 * ClassName:       RoutingTest
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 23:08
 * Version:         1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoutingTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void runEmpty() throws Exception {
        String exchangeName = "spring-routing-ex";
//        String routingKey = "laoshiren";
         String routingKey = "rabbit";
        Map<String,Object> map = new HashMap<>();
        map.put("key","spring-routing");
        map.put("exchangeName",exchangeName);
        map.put("routingKey",routingKey);
        String jsonStr = objectMapper.writeValueAsString(map);
        // 1. exchange 名
        // 2 routing 模式必须跟上路由key
        // 3 消息
        rabbitTemplate.convertAndSend(exchangeName,routingKey,jsonStr);
    }

}
