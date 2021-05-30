package com.laoshiren.hello.rabbit.tx.amqp.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoshiren.hello.rabbit.tx.amqp.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * ProjectName:     hello-rabbit-mq 
 * Package:         com.laoshiren.hello.rabbit.tx.amqp.order.mapper
 * ClassName:       OrderMapper
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     TBD 
 * Date:            2021/5/30 23:14
 * Version:         1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}