package com.dream.demo.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://192.168.22.160:8062","http://192.168.22.3:8062").allowCredentials(true)
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                .allowedHeaders("*").maxAge(3600);
    }
}
