package com.example.impl;

import com.example.bean.order;
import com.example.bean.product;
import com.example.service.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class orderServiceImpl implements orderService {

    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public order CreateOrder(Long userId, Long productId) {
        order order = new order();
        product product = getProductFromRemote(productId);
        order.setId(1L);
        //计算订单总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal("10")));
        order.setUserId(userId);
        order.setNikeName("zhangsan");
        order.setAddress("beijing");
        //远程查询商品信息，并设置到订单中
        order.setProductList(Arrays.asList(product));
        return order;
    }


    private product getProductFromRemote(Long productId) {
        //1.获取商品服务所在的IP地址
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(1);       //
        //2.构造请求URL
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        //3.发送请求，获取商品信息
        log.info("请求URL:{}", url);
        product product = restTemplate.getForObject(url, product.class);
        return product;
    }



}
