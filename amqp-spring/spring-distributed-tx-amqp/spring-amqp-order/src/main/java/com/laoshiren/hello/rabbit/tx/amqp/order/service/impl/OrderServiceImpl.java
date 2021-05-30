package com.laoshiren.hello.rabbit.tx.amqp.order.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoshiren.hello.rabbit.tx.amqp.order.domain.Order;
import com.laoshiren.hello.rabbit.tx.amqp.order.mapper.OrderMapper;
import com.laoshiren.hello.rabbit.tx.amqp.order.service.OrderService;
/**
 * ProjectName:     hello-rabbit-mq 
 * Package:         com.laoshiren.hello.rabbit.tx.amqp.order.service.impl
 * ClassName:       OrderServiceImpl
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     TBD 
 * Date:            2021/5/30 23:14
 * Version:         1.0.0
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

}
