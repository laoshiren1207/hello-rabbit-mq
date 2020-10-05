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

    /**
     * 获取连接
     * @return com.rabbitmq.client.Connection
     * @throws java.io.IOException Exception
     */
    public static Connection openConnection() throws Exception {
        // 链接MQ工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接主机
        factory.setHost("120.79.0.210");
        factory.setPort(5672);
        // 设置虚拟主机，
        factory.setVirtualHost("/");
        factory.setUsername("rabbit");
        factory.setPassword("123456");
        return factory.newConnection();
    }

    /**
     * 关闭资源
     * @param channel   通道对象
     * @param connection    连接
     * @throws java.io.IOException Exception
     */
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
