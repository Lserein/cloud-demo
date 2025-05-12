package com.example.controller;

import com.example.bean.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.example.service.orderService;

@RefreshScope
@RestController
public class orderController {

    @Autowired
    private orderService orderService;

    @Value("${order.timeout}")
    String orderTimeouut;
    @Value("${order.auto-confirm}")
    String orderConfirm;

    @GetMapping("/config")
    String config(){
        System.out.println("orderTimeouut:"+orderTimeouut);
        System.out.println("orderConfirm:"+orderConfirm);
        return orderTimeouut+" "+orderConfirm;
    }

    @GetMapping("/order/create")
    //创建订单
    public order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        order order = orderService.CreateOrder(userId, productId);
        return order;
    }
}
