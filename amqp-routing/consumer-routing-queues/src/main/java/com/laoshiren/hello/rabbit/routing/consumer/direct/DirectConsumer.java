package com.laoshiren.hello.rabbit.routing.consumer.direct;

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
 * Package:         com.laoshiren.hello.rabbit.routing.consumer.direct
 * ClassName:       DirectConsumer
 * Author:          laoshiren
 * Description:     路由直连消费者1
 * Date:            2020/10/5 22:41
 * Version:         1.0
 */
public class DirectConsumer {

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = RabbitUtils.openConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 设置交换机
        channel.exchangeDeclare("routing-direct-ex", BuiltinExchangeType.DIRECT,true);
        // 路由key
        String routingKey = "routing Key 1";
        // 临时队列名
        String queueName = channel.queueDeclare().getQueue();
        // 绑定队列，使用路由key
        channel.queueBind(queueName,"routing-direct-ex",routingKey,null);
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)throws IOException{
                System.out.print("consumer :  "+ envelope.getRoutingKey());
                String json =  new String(body);
                System.out.println(json);
            }
        });
    }

}
