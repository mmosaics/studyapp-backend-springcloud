package org.example.filter;

import com.hlr.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationFilter implements GatewayFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public int getOrder() {
        return 50;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        logger.info("TokenFilter Start");

        //获取session信息
        String sessionId = exchange.getRequest().getHeaders().getFirst(Constant.AUTH_HEADER);


        //if(sessionId != null) {
        //   isLogin = authenticationUserService.authUser(sessionId);
        //}

        if( sessionId == null || sessionId.isEmpty()) {

            return Mono.defer(() -> {

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                final ServerHttpResponse response =  exchange.getResponse();

                byte[] bytes = "{\"code\":\"99999\",\"message\":\"非法访问,token错误\"}".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                response.getHeaders().set("aaa", "bbb");
                logger.info("TokenFilter拦截非法请求, 没有监测到token或是token错误....");

                return response.writeWith(Flux.just(buffer));//设置body
            });

        }

        return chain.filter(exchange);

    }
}
