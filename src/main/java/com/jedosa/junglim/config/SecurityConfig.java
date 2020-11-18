package com.jedosa.junglim.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // static resources
                .mvcMatchers("/lib/**", "/css/**", "/js/**", "/fonts/**", "/h2-console/**", "/favicon.ico").permitAll()
                // views
                .mvcMatchers("/", "sign-up", "index", "account/**").permitAll()
                //.mvcMatchers(HttpMethod.GET, "/profile/*").permitAll() // GET 요청일 때만 허용
                .anyRequest().authenticated()
//                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().csrf().disable()
                .headers().frameOptions().disable();
        //
//        http.csrf().ignoringAntMatchers("/h2-console/**");
//        http.headers().frameOptions().disable();
    }
}
