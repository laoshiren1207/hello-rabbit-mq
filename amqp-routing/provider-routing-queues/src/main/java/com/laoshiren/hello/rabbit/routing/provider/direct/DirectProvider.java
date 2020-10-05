package com.laoshiren.hello.rabbit.routing.provider.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.commons.RabbitUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.routing.provider
 * ClassName:       DirectProvider
 * Author:          laoshiren
 * Description:     路由直连生产者
 * Date:            2020/10/5 22:30
 * Version:         1.0
 */
public class DirectProvider {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = RabbitUtils.openConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 设置交换机
        // BuiltinExchangeType.DIRECT 直连
        channel.exchangeDeclare("routing-direct-ex", BuiltinExchangeType.DIRECT,true);
        // 声明路由Key
        String routingKey = "routing Key 1";
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("key",routingKey);
        objectMap.put("value","routing -- direct");
        String jsonStr = objectMapper.writeValueAsString(objectMap);
        // 消息生产者 指定路由Key
        channel.basicPublish("routing-direct-ex",routingKey,null,jsonStr.getBytes());

        RabbitUtils.closeResource(channel,connection);
    }

}
