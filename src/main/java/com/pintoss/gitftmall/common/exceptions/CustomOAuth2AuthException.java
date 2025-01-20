package com.pintoss.gitftmall.common.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomOAuth2AuthException extends AuthenticationException {
    public CustomOAuth2AuthException(String msg) {
        super(msg);
    }
}