package com.pintoss.gitftmall.infra.security.filter.jwt;

import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.infra.security.service.SecurityService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final HttpServletUtils servletUtils;
    private final SecurityService securityService;

    public JwtFilter(TokenProvider tokenProvider, HttpServletUtils servletUtils, SecurityService securityService) {
        this.tokenProvider = tokenProvider;
        this.servletUtils = servletUtils;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request);

        // 액세스 토큰 존재
        if ( accessToken != null ) {
            //  유효한 액세스 토큰
            if ( tokenProvider.validateToken(accessToken) ) {
                setAuthentication(accessToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = securityService.getAuthentication(tokenProvider.getSubject(accessToken));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        return servletUtils.getCookie(request, "AccessToken")
                .map(Cookie::getValue)
                .orElse(null);
    }

}

