package com.example;

import com.example.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {

    @Autowired
    WeatherFeignClient weatherFeignClient;

    @Test
    void test(){
        String weather = weatherFeignClient.getWeather("北京", "1");
        System.out.println(weather);
    }
}
