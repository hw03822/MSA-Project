package com.example.apigateway_service.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    private static final Logger logger = Logger.getLogger(CustomFilter.class.getName());

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Glob Pre Filter 사전 처리
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            logger.info("Custom PRE Filter: request id -> " + request.getId());

            // Custom Post Filter 사후처리
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("Custom POST filter code -> " + response.getStatusCode());
            }));
        };
    }

    public static class Config {
        // Put the Configuration properties
    }
}
