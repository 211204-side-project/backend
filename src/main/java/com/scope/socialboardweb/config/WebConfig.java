package com.scope.socialboardweb.config;

import com.scope.socialboardweb.interceptor.AuthorizationInterceptor;
import com.scope.socialboardweb.utils.annotation.resolver.RequestAttributeArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestAttributeArgumentResolver requestAttributeArgumentResolver;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("https://master.d2pi1b7mbo7sh5.amplifyapp.com/**", "/**:3000/")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "HEAD", "PATCH", "PUT", "DELETE");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/api/user/signup/**", "/api/user/signin/**", "/test/token", "api/auth/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestAttributeArgumentResolver);
    }
}
