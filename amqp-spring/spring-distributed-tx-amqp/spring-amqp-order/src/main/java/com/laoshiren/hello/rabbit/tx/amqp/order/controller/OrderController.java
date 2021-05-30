package com.laoshiren.hello.rabbit.tx.amqp.order.controller;

import com.laoshiren.hello.rabbit.commons.objects.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProjectName:     hello-rabbit-mq
 * Package:         com.laoshiren.hello.rabbit.tx.amqp.order.controller
 * ClassName:       OrderController
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     TBD
 * Date:            2021/5/30 23:00
 * Version:         1.0.0
 */
@RestController("/order")
@Slf4j
public class OrderController {

    @PostMapping(value = "/create")
    public ResponseResult<Void> createOrder() {
        log.info("创建订单 {}", "");
        ResponseResult<Void> result = new ResponseResult<>(200, "", null);

        return  result;
    }

}
