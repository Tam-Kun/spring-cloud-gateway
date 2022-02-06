package com.example.mygateway.config;

import com.example.mygateway.filter.JwtRequestFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, JwtRequestFilter jwtFilter) {
        return builder.routes()
                .route(p -> p
                        .path("/hi/**")
//                        .filters(f -> f
//                                .filter(jwtFilter.apply(new JwtRequestFilter.Config("dummy", true, false))))
                        .uri("http://tc01:8083/")
                )
                .build();
    }
}
