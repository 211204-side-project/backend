package com.scope.socialboardweb.utils.resolver;

import com.scope.socialboardweb.dto.UserRequestDto;
import com.scope.socialboardweb.utils.annotation.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserRequestArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isCorrectParameterType = parameter.getParameterType().equals(UserRequestDto.class);
        boolean isCorrectAnnotation = parameter.hasParameterAnnotation(LoginUser.class);

        return isCorrectParameterType && isCorrectAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Object loginUser = httpServletRequest.getAttribute("loginUser");
        return loginUser;
    }
}
