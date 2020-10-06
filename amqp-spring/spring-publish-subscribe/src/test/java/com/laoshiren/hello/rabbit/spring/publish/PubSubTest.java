package com.laoshiren.hello.rabbit.spring.publish;

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
 * Package:         com.laoshiren.hello.rabbit.spring.publish
 * ClassName:       PubSubApplication
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 22:36
 * Version:         1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PubSubTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void runEmpty() throws Exception{
        String exchangeName = "spring-fanout-ex";
        Map<String,Object> map = new HashMap<>();
        map.put("key","spring-Publish-Subscribe");
        map.put("exchangeName",exchangeName);
        String jsonStr = objectMapper.writeValueAsString(map);
        // 1 交换机名称
        // 2 路由key 发布订阅者 无须设置路由key
        // 3 消息体
        rabbitTemplate.convertAndSend(exchangeName,"",jsonStr);
    }

}
