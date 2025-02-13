package com.pintoss.gitftmall.domain.membership.infra.security.authentication;

import org.springframework.security.core.Authentication;

public interface TokenAuthenticationProvider {
    Authentication getAuthentication(String memberId);
}
