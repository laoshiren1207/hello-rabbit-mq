package com.laoshiren.hello.rabbit.work.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.work.consumer
 * ClassName:       SlowConsumer
 * Author:          laoshiren
 * Description:     处理速度较慢的消费者
 * Date:            2020/10/4 22:37
 * Version:         1.0
 */
public class SlowConsumer {

    public static void main(String[] args) throws Exception {
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
        // 一次只消费一个消息
        channel.basicQos(1);
        // 必须和发送端一直，不然就会新建一个channel
        channel.queueDeclare("amqp-work-queue",true,false,false,null);
        // 1 待消费的队列名称
        // 2 开始消息的自动确认机制
        // 3 消费的回调接口
        channel.basicConsume("amqp-work-queue",false,new DefaultConsumer(channel) {
            // body 表示消息队列取出的消息体
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Thread.sleep(300);
                System.out.println("slow-consumer-1");
                String json =  new String(body);
                System.out.println(json);
                // 1 确认队列中具体消息
                // 2 是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
