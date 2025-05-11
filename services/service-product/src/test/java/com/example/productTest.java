package com.example;


import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class productTest {

    @Autowired
    DiscoveryClient discoveryClient;   //获取服务信息

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;  //获取服务信息


    @Test
    void nacosServiceDiscovery() throws NacosException {
        // 服务注册与发现功能的测试代码
        nacosServiceDiscovery.getServices().forEach(service ->
                System.out.println("service"+service)
        );
        //获取服务的IP和端口信息
        nacosServiceDiscovery.getInstances("product-service").forEach(instance ->
                System.out.println("instance"+instance.getHost()+"\n"+instance.getPort())
        );
    }

    @Test
    public void discoveryClient() {
        // 服务注册与发现功能的测试代码
        discoveryClient.getServices().forEach(service ->
                System.out.println("service"+service)
        );
        //获取服务的IP和端口信息
        discoveryClient.getInstances("product-service").forEach(instance ->
                System.out.println("instance"+instance.getHost()+"\n"+instance.getPort()+"\n"+instance.getUri())
        );
    }
}
