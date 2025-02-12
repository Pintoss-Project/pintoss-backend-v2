package com.pintoss.gitftmall.core.security.authentication;

import org.springframework.security.core.Authentication;

public interface SecurityAuthenticationProvider {
    Authentication getAuthentication(String memberId);
}
