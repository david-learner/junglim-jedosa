package com.jedosa.junglim.interceptor;

import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NoLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인 하지 않은 사용자를 게스트로 식별할 수 있게 세션에 Account객체(Guest)를 저장하는 인터셉터
 */
public class GuestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log =  LoggerFactory.getLogger(GuestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 게스트 식별 인터셉터
        SessionAccountDto sessionAccount = (SessionAccountDto) request.getSession().getAttribute(SessionAccount.KEY);
        if (sessionAccount == null) {
            log.info("INTERCEPTOR:: This user is GUEST");
            request.getSession().setAttribute(SessionAccount.KEY, SessionAccountDto.GUEST);
        }
        return super.preHandle(request, response, handler);
    }
}
