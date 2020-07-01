package org.example.filter;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class UrlFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("UrlFilter开始............");
        URI uri = exchange.getRequest().getURI();
        logger.info("UrlFilter开始............URI===" + uri.toString());

        HttpMethod method = exchange.getRequest().getMethod();
        logger.info("UrlFilter开始............HttpMethod===" + method.toString());

        String methodValue = exchange.getRequest().getMethodValue();
        logger.info("UrlFilter开始............getMethodValue===" + methodValue);


        RequestPath requestPath = exchange.getRequest().getPath();
        logger.info("UrlFilter开始............RequestPath===" + requestPath.toString());

        InetSocketAddress inetSocketAddress = exchange.getRequest().getRemoteAddress();
        logger.info("UrlFilter开始............InetSocketAddress===" + inetSocketAddress.toString());

        /*
        //拦截的逻辑。根据具体业务逻辑做拦截。
        String perm = exchange.getRequest().getQueryParams().getFirst("perm");
        if (perm == null || perm.isEmpty()) {
            logger.info("perm is empty...");
            //设置HttpStatus，返回体是空的
//					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//					return exchange.getResponse().setComplete();

            //下面可以设置返回体的具体内容
            //设置status和body
            return Mono.defer(() -> {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//设置status
                final ServerHttpResponse response = exchange.getResponse();
                byte[] bytes = "{\"code\":\"99000\",\"message\":\"非法访问,没有检测到权限码perm~~~~~~\"}".getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                response.getHeaders().set("permValid", "false");//设置header
                logger.info("UrlFilter拦截非法请求，没有检测到权限码perm............");
                return response.writeWith(Flux.just(buffer));//设置body
            });
        }
        */

        return chain.filter(exchange);

    }
}
