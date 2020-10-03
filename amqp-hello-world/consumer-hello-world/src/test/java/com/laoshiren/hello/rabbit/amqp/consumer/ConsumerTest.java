package com.laoshiren.hello.rabbit.amqp.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.amqp.consumer
 * ClassName:       ConsumerTest
 * Author:          laoshiren
 * Description:     Consumer Test
 * Date:            2020/10/3 22:39
 * Version:         1.0
 */
public class ConsumerTest {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 常规设置
        factory.setHost("120.79.0.210");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("rabbit");
        factory.setPassword("123456");
        // 创建新连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 必须和发送端一直，不然就会新建一个channel
        channel.queueDeclare("amqp-hello-queue",false,false,false,null);
        // 1 待消费的队列名称
        // 2 开始消息的自动确认机制
        // 3 消费的回调接口
        channel.basicConsume("amqp-hello-queue",true,new DefaultConsumer(channel) {
            // body 表示消息队列取出的消息体
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String json =  new String(body);
                System.out.println(json);
            }
        });
    }

}
