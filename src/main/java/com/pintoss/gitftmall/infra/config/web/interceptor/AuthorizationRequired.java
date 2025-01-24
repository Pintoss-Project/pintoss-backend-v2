package com.pintoss.gitftmall.infra.config.web.interceptor;

import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthorizationRequired {
    RoleEnum[] value();

    String failureMessage() default "접근 권한이 없습니다.";

    HttpStatus status() default HttpStatus.UNAUTHORIZED;
}
