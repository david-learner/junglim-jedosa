package com.jedosa.junglim.interceptor;

import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NotAdminException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionAccountDto sessionAccount = (SessionAccountDto) request.getSession().getAttribute(SessionAccount.KEY);
        if (!sessionAccount.isAdmin()) {
            throw new NotAdminException();
        }
        return super.preHandle(request, response, handler);
    }
}
