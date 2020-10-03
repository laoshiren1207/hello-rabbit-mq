package com.laoshiren.hello.rabbit.amqp.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.amqp.provider
 * ClassName:       ProviderTest
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     TBD
 * Date:            2020/10/3 17:39
 * Version:         1.0.0
 */

public class ProviderTest {

    // 没有映入spring boot starter web 只能自己new
    public ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        // 链接MQ工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接主机
        factory.setHost("120.79.0.210");
        factory.setPort(5672);
        // 设置虚拟主机，
        factory.setVirtualHost("/");
        factory.setUsername("rabbit");
        factory.setPassword("123456");
        // 获取连接
        Connection connection = factory.newConnection();
        // 获取通道 channel
        Channel channel = connection.createChannel();
        // chanel 绑定消息队列
        // 1. queue 队列名字，队列不存在自动创建
        // 2. durable 队列是否持久化
        // 3. exclusive 是否独占
        // 4. autoDelete 是否在消费完成之后自动删除队列
        // 5. 额外参数
        channel.queueDeclare("amqp-hello-queue",false,false,false,null);

        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("key","value");
        String json = objectMapper.writeValueAsString(objectMap);
        // 发布消息
        // 1.交换机名称，没有传递空字符串
        // 2.指定队列
        // 3.传递参数
        // 4.消息对象
        channel.basicPublish("","amqp-hello-queue",null,json.getBytes());
        // 关闭通道
        channel.close();
        // 关闭连接
        connection.close();
    }


}
