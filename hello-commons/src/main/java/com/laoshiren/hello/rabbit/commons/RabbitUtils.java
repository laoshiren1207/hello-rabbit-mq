package com.laoshiren.hello.rabbit.commons;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.commons
 * ClassName:       RabbitUtils
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/5 22:42
 * Version:         1.0
 */
public class RabbitUtils {

    public static Connection openConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.79.0.210");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("rabbit");
        factory.setPassword("123456");
        return factory.newConnection();
    }

    public static void closeResource(Channel channel,Connection connection) throws Exception {
        try {
            if (channel!=null )
                channel.close();
        } finally {
            if (connection!=null)
                connection.close();
        }
    }

}
