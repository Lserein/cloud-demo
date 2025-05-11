package com.example.controller;

import com.example.bean.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.service.orderService;

@RestController
public class orderController {

    @Autowired
    private orderService orderService;

    @GetMapping("/order/create")
    //创建订单
    public order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        order order = orderService.CreateOrder(userId, productId);
        return order;
    }
}
