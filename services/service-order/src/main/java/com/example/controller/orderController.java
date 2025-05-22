package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.bean.order;
import com.example.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.example.service.orderService;

@RequestMapping("/api/order")
@RefreshScope
@RestController
public class orderController {

    @Autowired
    private orderService orderService;

    @Autowired
    OrderProperties orderProperties;


    @GetMapping("/config")
    String config(){
        System.out.println("orderTimeouut:"+orderProperties.getTimeout());
        System.out.println("orderConfirm:"+orderProperties.getAutoconfirm());
        return orderProperties.getTimeout()+":"+orderProperties.getAutoconfirm()
                +":"+orderProperties.getDburl();
    }

    @GetMapping("/order/create")
    //创建订单
    public order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        order order = orderService.CreateOrder(userId, productId);
        return order;
    }

    //秒杀订单接口
    @GetMapping("/order/seckill")
    @SentinelResource(value = "seckill",fallback = "seckillFallback")
    public order seckill(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        order order = orderService.CreateOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public order seckillFallback(Long userId, Long productId, BlockException e) {
        order order = new order();
        order.setAddress("兜底地址"+e.getMessage());
        order.setId(userId);
        order.setUserId(userId);
        return order;
    }





    @GetMapping("writedb")
    public String writeDb(){
        return "write db success...";
    }

    @GetMapping("readdb")
    public String readDb(){
        return "read db success...";
    }


}
