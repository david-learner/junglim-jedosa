package com.jedosa.junglim.infrastructure.resolver;

import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NoLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(LoginHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(Login.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        SessionAccountDto sessionAccountDto =
                (SessionAccountDto) webRequest.getAttribute(SessionAccount.KEY, RequestAttributes.SCOPE_SESSION);

        // 로그인 하지 않은 사용자
        if (sessionAccountDto == null) {
            throw new NoLoginException();
        }

        return sessionAccountDto;
    }
}
