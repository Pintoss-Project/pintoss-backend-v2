package com.pintoss.gitftmall.infra.security.oauth.handler;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.result.LoginResult;
import com.pintoss.gitftmall.domain.membership.controller.request.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final RestTemplate restTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Attributes에서 데이터 가져오기
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // email과 password 추출
        String email = (String) attributes.get("email");
        String password = (String) attributes.get("password");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        String loginUrl = "http://localhost:8080/api/users/login";

        ApiResponse<LoginResult> apiResponse = restTemplate.postForObject(loginUrl, loginRequest, ApiResponse.class);

        // 성공 후 리다이렉트
        response.sendRedirect("/oauth2/success");
    }
}
