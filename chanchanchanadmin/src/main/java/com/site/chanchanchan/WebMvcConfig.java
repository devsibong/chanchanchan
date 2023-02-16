package com.site.chanchanchan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new Interceptor())
    			.addPathPatterns("/**")
    			.excludePathPatterns("/login")
    			.excludePathPatterns("/**/*.js")
    			.excludePathPatterns("/**/*.css")
    			.excludePathPatterns("/**/**/*.css")
    			.excludePathPatterns("/**/**/*.js")
    			.excludePathPatterns("/**/**/**/*.css")
    			.excludePathPatterns("/**/**/**/*.js")
    			.excludePathPatterns("/register");
    }
}