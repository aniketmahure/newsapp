package com.newsApp.ApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {
    private static Logger logger = LoggerFactory.getLogger(GatewayConfig.class);
   /*
   * create a method to Route request
    */
    @Bean
    public RouteLocator newsApp(RouteLocatorBuilder routeLocatorBuilder){
        logger.info("route to newsApp");
        return routeLocatorBuilder.routes().route("NewsApp",r -> r.path("/news/**").uri("http://localhost:7003")).build();
    }
    @Bean
    public RouteLocator userApp(RouteLocatorBuilder routeLocatorBuilder){
        logger.info("route to userApp");
        return routeLocatorBuilder.routes().route("UserApp",r -> r.path("/user/**").uri("http://localhost:7001")).build();
    }
    @Bean
    public RouteLocator AuthenticationApp(RouteLocatorBuilder routeLocatorBuilder){
        logger.info("route to authApp");
        return routeLocatorBuilder.routes().route("AuthenticationApp",r -> r.path("/auth/**").uri("http://localhost:7002")).build();
    }
    @Bean
    public RouteLocator FavouriteApp(RouteLocatorBuilder routeLocatorBuilder){
        logger.info("route to favouriteApp");
        return routeLocatorBuilder.routes().route("FavouriteApp",r -> r.path("/favorite/**").uri("http://localhost:7004")).build();
    }
}