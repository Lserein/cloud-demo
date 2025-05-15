package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//https://v.api.aa1.cn/api/api-tianqi-3/index.php?msg=%E5%A4%A9%E6%B4%A5&type=1
@FeignClient(value = "weather",url = "https://v.api.aa1.cn/api/api-tianqi-3/index.php")
public interface WeatherFeignClient {

    @GetMapping("/api/api-tianqi-3/index.php")
    String getWeather(@RequestParam("msg") String msg,
                    @RequestParam("type") String type);

}
