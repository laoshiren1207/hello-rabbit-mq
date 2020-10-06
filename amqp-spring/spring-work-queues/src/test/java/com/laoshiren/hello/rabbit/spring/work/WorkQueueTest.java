package com.laoshiren.hello.rabbit.spring.work;

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
 * Package:         com.laoshiren.hello.rabbiy.spring.work
 * ClassName:       WorkQueueTest
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 20:26
 * Version:         1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkQueueTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void runEmpty() throws Exception {
        for (int i = 0; i < 20; i++) {
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("key","spring-work-queue");
            objectMap.put("value",i);
            String jsonStr = objectMapper.writeValueAsString(objectMap);
            rabbitTemplate.convertAndSend("spring-work-queue",jsonStr);
        }

    }

}
