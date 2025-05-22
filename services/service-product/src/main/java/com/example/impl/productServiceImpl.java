package com.example.impl;

import com.example.bean.product;
import com.example.service.productService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class productServiceImpl implements productService {
    @Override
    public product getProductById(Long productId) {
        product product = new product();
        product.setId(productId);
        product.setProductName("iphone");
        product.setPrice(new BigDecimal("1000.00"));
        product.setNum(1);
/*        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return product;
    }
}
