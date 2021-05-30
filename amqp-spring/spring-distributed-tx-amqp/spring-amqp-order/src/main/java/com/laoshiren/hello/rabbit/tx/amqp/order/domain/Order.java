package com.laoshiren.hello.rabbit.tx.amqp.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProjectName:     hello-rabbit-mq 
 * Package:         com.laoshiren.hello.rabbit.tx.amqp.order.domain
 * ClassName:       Order
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:     TBD 
 * Date:            2021/5/30 23:14
 * Version:         1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 35592454642889552L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @TableField(value = "order_no")
    private String orderNo;

    @TableField(value = "sku_code")
    private String skuCode;

    @TableField(value = "sku_name")
    private String skuName;

    @TableField(value = "sku_quantity")
    private Integer skuQuantity;

}