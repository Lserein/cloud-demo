package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    //
    @LoadBalanced  //开启负载均衡器
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
