package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.UUID;


@Component
public class OnceTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<OnceTokenGatewayFilterFactory.Config> {
    public OnceTokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();

            String value = config.getValue();
            if (value == null || value.isEmpty()) {
                value = UUID.randomUUID().toString(); // 默认生成 UUID
            } else if (value.equals("uuid")) {
                value = UUID.randomUUID().toString();
            } else if (value.equals("jwt")) {
                value = "jwt_token";
            }

            headers.add("once-token", value);
        }));
    }

    public static class Config {
        private String value; // 移除 @NotEmpty

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}