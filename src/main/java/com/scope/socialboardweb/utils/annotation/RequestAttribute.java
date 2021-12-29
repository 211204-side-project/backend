package com.scope.socialboardweb.utils.annotation;

import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.*;

@Deprecated
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAttribute {
    String value() default "";
    boolean required() default true;
    String defaultValue() default ValueConstants.DEFAULT_NONE;
}
