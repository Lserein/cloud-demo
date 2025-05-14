package com.example.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order") //配置批量绑定，在Nacos环境下无需配置自动刷新
public class OrderProperties {

    String timeout;

    String autoconfirm;

    String dburl;
}
