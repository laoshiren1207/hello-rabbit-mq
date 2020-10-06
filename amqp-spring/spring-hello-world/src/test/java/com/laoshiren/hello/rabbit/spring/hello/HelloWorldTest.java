package com.laoshiren.hello.rabbit.spring.hello;

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
 * Package:         com.laoshiren.hello.rabbit.spring.hello
 * ClassName:       HelloWorldTest
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 16:10
 * Version:         1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloWorldTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void runEmpty() throws Exception {
        String queueName = "amqp-spring-hello";
        Map<String,Object> map = new HashMap<>();
        map.put("key","spring-hello-world");
        map.put("queueName",queueName);
        String jsonStr = objectMapper.writeValueAsString(map);
        // 向队列发送消息 消息生产者不会主动创建队列，只有有了消费者才会创建队列
        // 1 队列名
        // 2 消息体
        rabbitTemplate.convertAndSend(queueName,jsonStr);
    }

}
