package com.laoshiren.hello.rabbit.topic.consumer;

import com.laoshiren.hello.rabbit.commons.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.topic.consumer
 * ClassName:       TopicConsumer
 * Author:          laoshiren
 * Description:     Topic 消息消费者
 * Date:            2020/10/6 15:33
 * Version:         1.0
 */
public class TopicConsumer1 {

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitUtils.openConnection();
        // 声明通道
        Channel channel = connection.createChannel();
        // 声明交换机
        String exchangeName = "topic-ex";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC,true);
        // 声明队列并绑定
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,exchangeName,"rabbit.#",null);
        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException
            {
                System.out.print("topic-consumer2 "+ envelope.getExchange()+" routing key "+envelope.getRoutingKey()+" ");
                System.out.println(new String(body));
            }
        });
    }

}
