package com.example.apigateway;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class Customerfilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(Customerfilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        logger.info("++ req"+request);

        logger.info("++auth : "+request.getHeaders().getFirst("Authorization"));
        return chain.filter(exchange).then(Mono.fromRunnable(()->{

            ServerHttpResponse resp = exchange.getResponse();

            logger.info("res :  "+resp);

            logger.info("code :  "+resp.getStatusCode());

        }));

    }
}
