# RabbitMQ

## 1  前言

### 1.1  什么是MQ?

`MQ (Message Queue)`：翻译为**消息队列**，消息队列可以理解为一种在`TCP`协议之上构建的一个**简单的协议**，但它又不是具体的通信协议，而是更高层次的**通信模型 **即 **生产者** / **消费者**模型，通过定义自己的生产者和消费者实现消息通信从而屏蔽复杂的底层通信协议；它为分布式应用系统提供异步解耦和削峰填谷的能力，同时也具备互联网应用所需的海量消息堆积、高吞吐、可靠重试等特性。

> ... `MQ`是异步的，解耦用的，但是这个是`MQ`的效果而不是目的。`MQ`的真正目的是为了通讯，屏蔽底层复杂的通讯协议，定义了一套应用层更加简单的通讯协议。...

消息队列采用 `FIFO` 的方式，即 先进先出 的数据结构。

![](https://bkimg.cdn.bcebos.com/pic/8601a18b87d6277f8774fd792b381f30e924fc09)

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

![](https://www.rabbitmq.com/img/tutorials/python-one-overall.png)

#### provider

#### consumer

### amqp-work-queues

`Work Queues`任务模型也被称为`Task Queues`。当消息处理比较耗时的时候，可能产生的消息的速度远大于消耗消息的速度。长此以往消息就会堆积越来越多无法处理，此时就可以用`work`模型：**让多个消费者绑定一个队列，共同消费队列中的消息。**队列中的消息一旦被消费就会消失，因此任务是并不会被重复执行的。

![](https://www.rabbitmq.com/img/tutorials/python-two.png)

`rabbitmq`在`work`模式默认是顺序的将消息发给消费者，平均每个消费者消费消息的数量是一致的。**处理速度不一致也会导致消息堆积。**

#### provider

#### consumer

**能者多劳**：消息确认机制

~~~java
// 第二个参数 表示消息自动确认 autoAck
channel.basicConsume("amqp-work-queue",true,new DefaultConsumer(channel) {
      // body 表示消息队列取出的消息体
      @SneakyThrows
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
          Thread.sleep(5000);
          System.out.println("slow-consumer-1");
          String json =  new String(body);
          System.out.println(json);
      }
  });
~~~

消费者获取100个消息，当消费到第3个的时候消费者下线了，消息就会丢失98个。所以就需要设置`autoAck`设置为`false`。

`Unacked`表示未被确认的消息

![](https://img-blog.csdnimg.cn/20201004230819656.png)

~~~java
Channel channel = connection.createChannel();
// 一次只消费一个消息
channel.basicQos(1);
// 必须和发送端一直，不然就会新建一个channel
channel.queueDeclare("amqp-work-queue",true,false,false,null);
// 1 待消费的队列名称
// 2 开始消息的自动确认机制 true 表示自动确认 
// 3 消费的回调接口
channel.basicConsume("amqp-work-queue",false,new DefaultConsumer(channel) {
    // body 表示消息队列取出的消息体
    @SneakyThrows
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        Thread.sleep(1000);
        System.out.println("slow-consumer-1");
        String json =  new String(body);
        System.out.println(json);
        // 手动确认消息
        // 1 确认队列中具体消息
        // 2 是否开启多个消息同时确认
        channel.basicAck(envelope.getDeliveryTag(),false);
    }
});
~~~

### amqp-publish-subscribe

`fanout`也被称为广播模式，在广播模式下，**每个消费者有自己的队列，每个队列都必须绑定到交换机上**（图中的`x`）。**生产者发送消息只能发送给交换机**，交换机来决定给某个队列，生产者无法决定。交换机将消息发送给绑定上的队列，所有消费者都能拿到消息，**实现一条消息被多个消费者消费**。

![](https://www.rabbitmq.com/img/tutorials/python-three-overall.png)

#### provider

~~~java
Channel channel = connection.createChannel();
// 将通道声明指定的交换机
// 1 交换机的名称
// 2 交换机的类型 fanout 表示广播类型
channel.exchangeDeclare("fanout-Ex","fanout");
// 发送消息
Map<String,Object> objectMap = new HashMap<>();
objectMap.put("key","fanout");
String json = objectMapper.writeValueAsString(objectMap);
// 1 交换机名称
// 2 路由key,广播模式无须关心路由key
// 3 消息额外参数（持久化）
// 4 消息体
channel.basicPublish("fanout-Ex","",null,json.getBytes());
channel.close();
connection.close();
~~~

![](https://img-blog.csdnimg.cn/20201005154235475.png)

#### consumer

~~~java
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
        System.out.print("fanout-consumer-1 ");
        String json =  new String(body);
        System.out.println(json);
    }
});
~~~

![](https://img-blog.csdnimg.cn/20201005160615133.png)

开启2个消费者，交换机收到一条消息会被2个消费者消费。

![](https://img-blog.csdnimg.cn/2020100516085385.png)

### amqp-routing

#### provider

#### consumer

