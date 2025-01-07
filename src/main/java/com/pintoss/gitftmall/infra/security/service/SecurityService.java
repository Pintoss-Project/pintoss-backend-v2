package com.pintoss.gitftmall.infra.security.service;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    Authentication getAuthentication(String memberId);
}
