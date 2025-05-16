package com.example.controller;

import com.example.bean.product;
import jakarta.servlet.http.HttpServletRequest;
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
    public product getProduct(@PathVariable Long id, HttpServletRequest request) {
        product product = productService.getProductById(id);
        //获取请求头信息
        String header = request.getHeader("Authorization");
        System.out.println(header);
        System.out.println(product);
        return product;
    }


}
