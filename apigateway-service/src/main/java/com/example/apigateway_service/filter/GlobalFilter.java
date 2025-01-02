package com.example.apigateway_service.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    private static final Logger logger = Logger.getLogger(GlobalFilter.class.getName());

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Global Pre Filter 사전 처리
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            logger.info("Config object: " + config);

            logger.info("Global Filter baseMessage: " + config.getBaseMessage());

            if(config.isPreLogger()){
                logger.info("Global Filter Start: request id " + request.getId());
            }

            // Global Post Filter 사후처리
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPreLogger()) {
                    logger.info("Global Filter End: response code -> " + response.getStatusCode());
                }
            }));
        };
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
