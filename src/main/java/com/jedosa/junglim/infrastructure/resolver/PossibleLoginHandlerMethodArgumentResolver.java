package com.jedosa.junglim.infrastructure.resolver;

import com.jedosa.junglim.account.domain.Account;
import com.jedosa.junglim.account.domain.SessionAccount;
import com.jedosa.junglim.account.domain.SessionAccountDto;
import com.jedosa.junglim.exception.NoLoginException;
import com.jedosa.junglim.infrastructure.annotation.Login;
import com.jedosa.junglim.infrastructure.annotation.PossibleLogin;
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
 * 로그인하지 않은 사용자도 접근할 수 있음을 나타냄
 * ex) 주문문의 게시판의 경우 CRUD 모두 로그인한 사용자만 가능, 표지샘플, 공지사항 게시판의 경우 Read는 로그인하지 않아도 가능
 */
@Component
public class PossibleLoginHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(PossibleLoginHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PossibleLogin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        SessionAccountDto sessionAccountDto =
                (SessionAccountDto) webRequest.getAttribute(SessionAccount.KEY, RequestAttributes.SCOPE_SESSION);

        if (sessionAccountDto == null || sessionAccountDto.isGuest()) {
            log.info("로그인 하지 않은 사용자");
        }

        return sessionAccountDto;
    }
}
