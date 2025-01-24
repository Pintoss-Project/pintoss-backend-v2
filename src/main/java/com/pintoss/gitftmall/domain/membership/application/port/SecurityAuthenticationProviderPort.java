package com.pintoss.gitftmall.domain.membership.application.port;

import org.springframework.security.core.Authentication;

public interface SecurityAuthenticationProviderPort {
    Authentication getAuthentication(String memberId);
}
