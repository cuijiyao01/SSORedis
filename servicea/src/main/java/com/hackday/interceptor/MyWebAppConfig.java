package com.hackday.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class MyWebAppConfig implements WebMvcConfigurer {

    @Autowired
    private UserLoginHandlerInterceptor userLoginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(userLoginHandlerInterceptor).addPathPatterns("/mygithub/**");;
        registry.addInterceptor(userLoginHandlerInterceptor)
                .excludePathPatterns(Arrays.asList("/css/**", "/js/**", "/images/**", "/TileImg/**", "/Banner/**"))
                .addPathPatterns("/tv");;
    }
}
