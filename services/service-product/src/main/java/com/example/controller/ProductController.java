package com.example.controller;

import com.example.bean.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.productService;

@RestController
public class ProductController {

    @Autowired
    private productService productService;

    //查询商品列表
    @GetMapping("/product/{id}")
    public product getProduct(@PathVariable Long id) {
        product product = productService.getProductById(id);
        System.out.println(product);
        return product;
    }


}
