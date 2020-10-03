# RabbitMQ

## 1  前言

### 1.1  什么是MQ?

`MQ (Message Queue)`：翻译为**消息队列**，消息队列可以理解为一种在`TCP`协议之上构建的一个**简单的协议**，但它又不是具体的通信协议，而是更高层次的**通信模型 **即 **生产者** / **消费者**模型，通过定义自己的生产者和消费者实现消息通信从而屏蔽复杂的底层通信协议；它为分布式应用系统提供异步解耦和削峰填谷的能力，同时也具备互联网应用所需的海量消息堆积、高吞吐、可靠重试等特性。

> ... `MQ`是异步的，解耦用的，但是这个是`MQ`的效果而不是目的。`MQ`的真正目的是为了通讯，屏蔽底层复杂的通讯协议，定义了一套应用层更加简单的通讯协议。...

消息队列采用 `FIFO` 的方式，即 先进先出 的数据结构。

![](https://bkimg.cdn.bcebos.com/pic/8601a18b87d6277f8774fd792b381f30e924fc09?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U2MA==,g_7,xp_5,yp_5)

### 1.2 消息队列类型

一般分为有`Broker`和没有`Broker`，然而主流的`MQ`都是有`Broker`的，通常有一台服务器作为`Broker`，所有的消息都通过它中转。生产者把消息发送给它就结束自己的任务了，`Broker`则把消息主动推送给消费者（或者消费者主动轮询）。然而此文章就简单的写一下有`Broker`的`RabbitMQ`的玩法。

### 1.3 Topic 

生产者会发送`key`和数据到`Broker`，由`Broker`比较`key`之后决定给哪个消费者。而`Rabbit MQ`是轻`topic`的代表，由`Broker`计算出`Key`对应的队列然后把数据交给队列。

![](C:\Users\Administrator\Desktop\rabbit mq\轻topic.png)

## 2  RabbitMQ 的安装

`docker`的安装方式

~~~yaml
version: '3.1'
services:
  rabbitmq:
    restart: always
    image: rabbitmq:management
    container_name: rabbitmq
    ports:
      - 5672:5672  # rabbitmq 的端口
      - 15672:15672  # web UI   的端口
    environment:
      TZ: Asia/Shanghai
      RABBITMQ_DEFAULT_USER: root
      RABBITMQ_DEFAULT_PASS: 123456
    volumes:
      - ./data:/var/lib/rabbitmq
~~~

启动`docker-compose up -d`，访问`ip:15672` 账号密码如`yaml`配置

### 2.1 web管理界面

![](C:\Users\Administrator\Desktop\rabbit mq\0001.png)

## 3 RabbitMQ 的使用

![](C:\Users\Administrator\Desktop\rabbit mq\0002.png)

### amqp-hello-world

#### provider

