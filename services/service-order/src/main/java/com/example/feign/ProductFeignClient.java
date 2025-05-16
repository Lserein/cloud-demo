package com.example.feign;

import com.example.bean.product;
import com.example.feign.fallback.ProductFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service",fallback = ProductFeignClientFallback.class)   //Feign客户端，指定服务名称
public interface ProductFeignClient {

    //MVC注解两套逻辑
    //标注在controller上，是接收http请求
    //标注在feign上，是用来发送http请求
    @GetMapping("/product/{productId}")   //指定远程服务接口
    product getProductById(@PathVariable("productId") Long productId);
}
