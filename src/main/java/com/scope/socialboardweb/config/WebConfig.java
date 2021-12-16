package com.scope.socialboardweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("https://master.d2pi1b7mbo7sh5.amplifyapp.com/**", "/**:3000/")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "HEAD", "PATCH", "PUT", "DELETE");
    }
}
