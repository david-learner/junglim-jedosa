package com.jedosa.junglim.interceptor;

import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NoLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log =  LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Optional<SessionAccountDto> sessionAccount =
                Optional.ofNullable((SessionAccountDto) request.getSession().getAttribute(SessionAccount.KEY));
        sessionAccount.ifPresent(account -> {
            log.debug("Logged in User Account is {}", account.getGrade().name());
            if (account.isGuest()) {
                throw new NoLoginException();
            }
        });

        return super.preHandle(request, response, handler);
    }
}
