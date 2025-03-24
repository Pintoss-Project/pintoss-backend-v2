package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.OAuth2Service;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2LoginSuccess;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2Response;
import com.pintoss.gitftmall.domain.membership.controller.response.OAuth2SignupRequired;
import com.pintoss.gitftmall.domain.membership.domain.vo.LoginType;
import com.pintoss.gitftmall.domain.membership.domain.vo.OAuth2ProviderType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/login")
    public ResponseEntity<Void> getOAuth2LoginUrl(@RequestParam("providerType") OAuth2ProviderType providerType) {
        String loginUrl = oAuth2Service.getOAuth2LoginUrl(providerType);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(loginUrl))
                .build();
    }

    @GetMapping("/callback/{providerType}")
    public ApiResponse<Void> oauthCallback(@PathVariable(value = "providerType") LoginType providerType, @RequestParam("code") String code, HttpServletResponse servletResponse) throws IOException {
        OAuth2Response response = oAuth2Service.handleOAuthLogin(providerType, code);

        if(response instanceof OAuth2SignupRequired) {
            OAuth2SignupRequired signupRequired = (OAuth2SignupRequired) response;
            servletResponse.sendRedirect("https://pin-toss.com/register?email="+ signupRequired.getEmail()+"&loginType="+providerType.toString());
        } else if (response instanceof OAuth2LoginSuccess) {
            OAuth2LoginSuccess loginSuccess = (OAuth2LoginSuccess) response;
            servletResponse.sendRedirect("https://pin-toss.com/login/social?accessToken="+ loginSuccess.getAccessToken());
        }

        return null;
    }
}
