package com.laoshiren.hello.rabbit.spring.publish;

import com.laoshiren.hello.rabbit.spring.publish.consumer.FanoutConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.spring.publish
 * ClassName:       PubSubApplication
 * Author:          laoshiren
 * Description:
 * Date:            2020/10/6 22:35
 * Version:         1.0
 */
@SpringBootApplication
public class PubSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubSubApplication.class,args);
    }

}
