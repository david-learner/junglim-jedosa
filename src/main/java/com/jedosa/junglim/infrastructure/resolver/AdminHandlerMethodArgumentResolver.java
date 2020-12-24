package com.jedosa.junglim.infrastructure.resolver;

import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NoLoginException;
import com.jedosa.junglim.exception.NotAdminException;
import com.jedosa.junglim.infrastructure.annotation.Admin;
import com.jedosa.junglim.infrastructure.annotation.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 접속한 사용자가 관리자인지 아닌지 식별
 */
@Component
public class AdminHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(AdminHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(Admin.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        SessionAccountDto sessionAccountDto =
                (SessionAccountDto) webRequest.getAttribute(SessionAccount.KEY, RequestAttributes.SCOPE_SESSION);

        // 로그인 하지 않은 사용자
        if (sessionAccountDto == null || sessionAccountDto.isGuest()) {
            throw new NoLoginException();
        }

        // 관리자가 아닌 사용자
        if (!sessionAccountDto.isAdmin()) {
            throw new NotAdminException();
        }

        return sessionAccountDto;
    }
}
