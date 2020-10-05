package com.laoshiren.hello.rabbit.fanout.provider;

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
 * Package:         com.laoshiren.hello.rabbit.fanout.provider
 * ClassName:       FanoutProvider
 * Author:          laoshiren
 * Description:     广播模式生产者
 * Date:            2020/10/5 15:31
 * Version:         1.0
 */
public class FanoutProvider {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // 常规设置

        Connection connection = RabbitUtils.openConnection();
        Channel channel = connection.createChannel();
        // 将通道声明指定的交换机
        // 1 交换机的名称
        // 2 交换机的类型 fanout 表示广播类型
        channel.exchangeDeclare("fanout-Ex","fanout",true);
        // 发送消息
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("key","fanout");
        String json = objectMapper.writeValueAsString(objectMap);
        // 1 交换机名称
        // 2 路由key,广播模式无须关心路由key
        // 3 消息额外参数（持久化）
        // 4 消息体
        channel.basicPublish("fanout-Ex","",null,json.getBytes());
        RabbitUtils.closeResource(channel,connection);
    }

}
