package com.example.impl;

import com.example.bean.order;
import com.example.bean.product;
import com.example.feign.ProductFeignClient;
import com.example.service.orderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public order CreateOrder(Long userId, Long productId) {
        order order = new order();
        product product = productFeignClient.getProductById(productId);
        //product product = getProductFromRemote2(productId);
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


    //完成负载均衡
    private product getProductFromRemote(Long productId) {
        // 1. 负载均衡获取商品服务所在的 IP 地址
        ServiceInstance choose = loadBalancerClient.choose("product-service");

        // 2. 构造请求 URL
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;

        // 3. 发送请求，获取商品信息
        log.info("请求URL: {}", url);
        product product = restTemplate.getForObject(url, product.class);
        return product;
    }

    //不存在负载均衡功能
    private product getProductFromRemote1(Long productId) {
        // 1. 获取商品服务所在的 IP 地址
        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
        if (instances == null || instances.isEmpty()) {
            throw new RuntimeException("No instances available for service-product");
        }
        // 选择第一个实例
        ServiceInstance serviceInstance = instances.get(0);

        // 2. 构造请求 URL
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;

        // 3. 发送请求，获取商品信息
        log.info("请求URL: {}", url);
        product product = restTemplate.getForObject(url, product.class);
        return product;
    }

    private product getProductFromRemote2(Long productId) {
        // 1. 获取商品服务所在的 IP 地址

        // 2. 构造请求 URL
        String url = "http://product-service/product/" + productId;
        // 3. 发送请求，获取商品信息
        log.info("请求URL: {}", url);
        product product = restTemplate.getForObject(url, product.class);
        return product;
    }



}
