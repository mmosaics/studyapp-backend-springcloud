package org.example.config;

import org.example.filter.AuthenticationFilter;
import org.example.filter.UrlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class FilterConfig {

    Logger logger = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public GlobalFilter authFilter() {
        return new UrlFilter();
    }

    @Bean
    public RouteLocator urlFilter(RouteLocatorBuilder builder) {
        logger.info("FilterConfig--urlFilterRouteLocator--");
        return builder.routes()
                .route(r -> r.path("/auth/logout")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://auth-service")
                        .id("logout-router")
                )
                .route(r -> r.path("/auth/login")
                        .uri("lb://auth-service")
                        .id("login-router")
                )
                .route(r -> r.path("/auth/register")
                        .uri("lb://auth-service")
                        .id("register-router")
                )
                .route(r -> r.path("/auth/session/**")
                        .uri("lb://auth-service")
                        .id("authsession-router")
                )
                .route(r -> r.path("/auth/echo/**")
                        .uri("lb://auth-service")
                        .id("echo-router")
                )
                .route(r -> r.path("/courses/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://course-service")
                        .id("course-router")
                )
                .route(r -> r.path("/users/**").
                        filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://user-service")
                        .id("users-router")
                )
                .route(r -> r.path("/exams/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://exam-service")
                        .id("exams-router")
                )
                .route(r -> r.path("/test-sheet/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://test-paper-service")
                        .id("testsheet-router")
                )
                .route(r -> r.path("/homework/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://homework-service")
                        .id("homework-router")
                )
                .route(r -> r.path("/question/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://question-service")
                        .id("question-router")
                )
                .route(r -> r.path("/wrong-question/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://wrong-question-service")
                        .id("wrong-question-router")
                )
                .route(r -> r.path("/live/**")
                        .filters(f -> f.filter(new AuthenticationFilter())
                        .addResponseHeader("urlFilterFlag", "pass"))
                        .uri("lb://live-stream-service")
                        .id("live-stream-router")
                )
                .build();

    }

}
