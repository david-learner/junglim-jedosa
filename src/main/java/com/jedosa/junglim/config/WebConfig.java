package com.jedosa.junglim.config;

import com.jedosa.junglim.infrastructure.resolver.AdminHandlerMethodArgumentResolver;
import com.jedosa.junglim.infrastructure.resolver.LoginHandlerMethodArgumentResolver;
import com.jedosa.junglim.infrastructure.resolver.PossibleLoginHandlerMethodArgumentResolver;
import com.jedosa.junglim.interceptor.AdminAuthorizationInterceptor;
import com.jedosa.junglim.interceptor.GuestInterceptor;
import com.jedosa.junglim.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인하지 않은 사용자 게스트로 인식
        registry.addInterceptor(new GuestInterceptor())
                .addPathPatterns("/**");
        
        // 로그인 필요한 페이지
        registry.addInterceptor(new LoginInterceptor())
                // 관리자 페이지
                .addPathPatterns("/admin/**")
                // 모든 게시판 등록 폼
                .addPathPatterns("/board/*/form");

        // 관리자 인증 필요한 페이지
        registry.addInterceptor(new AdminAuthorizationInterceptor())
                .addPathPatterns("/admin/**")
                .addPathPatterns("/menu/*/edit/**")
                // 게시판
                .addPathPatterns("/board/notice/form");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginHandlerMethodArgumentResolver());
        resolvers.add(new AdminHandlerMethodArgumentResolver());
        resolvers.add(new PossibleLoginHandlerMethodArgumentResolver());
    }
}
