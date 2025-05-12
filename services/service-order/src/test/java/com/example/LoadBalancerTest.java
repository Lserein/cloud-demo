package com.example;

import com.alibaba.nacos.shaded.io.grpc.LoadBalancer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    public void test(){
        ServiceInstance choose = loadBalancerClient.choose("product-service");
        System.out.println(choose.getHost()+":"+choose.getPort());
        ServiceInstance choose1 = loadBalancerClient.choose("product-service");
        System.out.println(choose1.getHost()+":"+choose1.getPort());
        ServiceInstance choose2 = loadBalancerClient.choose("product-service");
        System.out.println(choose2.getHost()+":"+choose2.getPort());
    }

}
