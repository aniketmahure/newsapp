package com.newsApp.FavoriteApp.config;

import com.newsApp.FavoriteApp.filter.JwtTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean jwtFavouriteFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new JwtTokenFilter());
        filter.addUrlPatterns("/favorite/*");
        return filter;
    }
}