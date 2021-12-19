package com.scope.socialboardweb.utils.resolver;

import com.scope.socialboardweb.utils.annotation.RequestAttribute;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Component
public class RequestAttributeArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestAttribute.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Annotation[] paramAnns = parameter.getParameterAnnotations();
        Class<?> paramType = parameter.getParameterType();

        for (Annotation paramAnn : paramAnns) {
            if (RequestAttribute.class.isInstance(paramAnn)) {
                RequestAttribute requestAttribute = (RequestAttribute) paramAnn;
                HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

                Object result = httpServletRequest.getAttribute(requestAttribute.value());
                return result;
            }
        }

        return WebArgumentResolver.UNRESOLVED;
    }
}
