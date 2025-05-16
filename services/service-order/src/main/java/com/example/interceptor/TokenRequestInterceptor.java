package com.example.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenRequestInterceptor implements RequestInterceptor {

    /*
    * 请求拦截器需要实现RequestInterceptor接口的apply方法
    * 该方法会在请求发送之前被调用
    * 可以在该方法中添加请求头、请求参数等
    * 这里我们可以通过RequestTemplate对象来添加请求头
    * 例如：requestTemplate.header("Authorization", "Bearer " + token);
    * */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("拦截器执行了");
        requestTemplate.header("Authorization", UUID.randomUUID().toString());
    }
}
