package com.example.mygateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtRequestFilter extends AbstractGatewayFilterFactory<JwtRequestFilter.Config> {
    final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

        public Config(String baseMessage, boolean preLogger, boolean postLogger) {
            this.baseMessage = baseMessage;
            this.postLogger = postLogger;
            this.preLogger = preLogger;
        }
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {
                String token = exchange.getRequest().getHeaders().get("Authorization").get(0).substring(7);
            } catch (NullPointerException e) {
                logger.warn("no token");
                exchange.getResponse().setStatusCode(HttpStatus.valueOf(401));
                logger.info("status code :" + exchange.getResponse().getStatusCode());
                return null;
            }

            return chain.filter(exchange);
        };
    }

}
