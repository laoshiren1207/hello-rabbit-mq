package com.laoshiren.hello.rabbit.fanout.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laoshiren.hello.rabbit.commons.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.fanout.consumer
 * ClassName:       FanoutConsumer
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/5 15:47
 * Version:         1.0
 */
public class FanoutConsumer2 {

    public static void main(String[] args) throws Exception {
        // 常规设置
        Connection connection = RabbitUtils.openConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("fanout-Ex","fanout",true);
        // 临时队列名
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列和交换机
        channel.queueBind(queueName,"fanout-Ex","");
        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel) {
            // body 表示消息队列取出的消息体
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.print("fanout-consumer-2 ");
                String json =  new String(body);
                System.out.println(json);
            }
        });

    }

}
