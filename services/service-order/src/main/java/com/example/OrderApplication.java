package com.example;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 项目启动时监听Nacos配置文件变化
     * 1. 获取最新的配置数据
     * 2. 发送邮件通知开发者
     */
    @Bean
    public ApplicationRunner runner(NacosConfigManager nacosConfigManager) {
        return args -> {
            NacosConfigService nacosConfigService = (NacosConfigService) nacosConfigManager.getConfigService();

            nacosConfigService.addListener(
                    "com.example.OrderProperties",
                    "defaultGroup",
                    new Listener() {
                        @Override
                        public Executor getExecutor() {
                            return Executors.newFixedThreadPool(4);
                        }

                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            System.out.println("配置文件更新了，最新的配置信息为：" + configInfo);

                            // 发送邮件通知开发者
                            // 此处省略发送邮件的代码
                            System.out.println("邮件已发送");
                        }
                    }
            );
        };
    }
}