package com.pintoss.gitftmall.common.exceptions.client;

import org.springframework.security.core.AuthenticationException;

public class CustomOAuthAuthenticationException extends AuthenticationException {
    public CustomOAuthAuthenticationException(String message) {
        super(message);
    }
}