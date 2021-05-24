package com.laoshiren.hello.rabbit.topic.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.commons.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.topic.provider
 * ClassName:       TopicProvider
 * Author:          laoshiren
 * Description:     topic 交换机消息
 * Date:            2020/10/6 15:16
 * Version:         1.0
 */
public class TopicProvider {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = RabbitUtils.openConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        String exchangeName = "topic-ex";
        // 声明一个topic类型的交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true);
        // 声明消息体
        String routingKey = "rabbit.laosh1iren.topic";
        Map<String,Object> map = new HashMap<>();
        map.put("routing key",routingKey);
        map.put("exchange", exchangeName);
        String jsonStr = objectMapper.writeValueAsString(map);
        // 向topic交换机发送消息
        channel.basicPublish(exchangeName,routingKey,null,jsonStr.getBytes());
        RabbitUtils.closeResource(channel,connection);
    }

}
