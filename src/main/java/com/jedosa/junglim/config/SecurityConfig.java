package com.jedosa.junglim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "sign-up", "/lib/**", "/css/**", "/js/**", "/fonts/**").permitAll()
                //.mvcMatchers(HttpMethod.GET, "/profile/*").permitAll() // GET 요청일 때만 허용
                .anyRequest().authenticated();
    }
}
