package com.jedosa.junglim.config;

import com.jedosa.junglim.interceptor.AdminAuthorizationInterceptor;
import com.jedosa.junglim.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**");

        registry.addInterceptor(new AdminAuthorizationInterceptor())
                .addPathPatterns("/admin/**")
                .addPathPatterns("/menu/*/edit/**");
    }
}
