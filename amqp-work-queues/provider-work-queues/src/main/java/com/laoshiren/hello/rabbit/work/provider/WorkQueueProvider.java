package com.laoshiren.hello.rabbit.work.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.commons.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.work.provider
 * ClassName:       WorkQueueProvider
 * Author:          laoshiren
 * Description:     循环发200次消息
 * Date:            2020/10/4 22:26
 * Version:         1.0
 */
public class WorkQueueProvider {

    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = RabbitUtils.openConnection();
        Channel channel = connection.createChannel();
        // 定义队列
        channel.queueDeclare("amqp-work-queue",true,false,false,null);
        // 生产消息
        for (int i = 0; i < 200 ; i++) {
            Map<String,Object> objectMap = new HashMap<>();
            objectMap.put("key "+i,"value "+i);
            String jsonStr = objectMapper.writeValueAsString(objectMap);
            channel.basicPublish("","amqp-work-queue",null,jsonStr.getBytes());
        }
        RabbitUtils.closeResource(channel,connection);
    }

}
