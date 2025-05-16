package com.example.feign.fallback;

import com.example.bean.product;
import com.example.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public product getProductById(Long productId) {
        System.out.println("兜底回调");
        product product = new product();
        product.setId(productId);
        product.setProductName("兜底回调商品");
        product.setPrice(new BigDecimal("1000.00"));
        product.setNum(10);
        return product;
    }
}
