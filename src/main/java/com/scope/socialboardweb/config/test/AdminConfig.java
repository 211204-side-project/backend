package com.scope.socialboardweb.config.test;

import com.scope.socialboardweb.interceptor.test.AdminLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AdminConfig implements WebMvcConfigurer {

    private final AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor)
            .addPathPatterns("/test/db/**")
            .excludePathPatterns("/test/db");
    }
}
