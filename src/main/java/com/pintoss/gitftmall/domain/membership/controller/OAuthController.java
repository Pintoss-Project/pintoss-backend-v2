package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class OAuthController {

    @GetMapping("/success")
    public ApiResponse<String> oauth2Success() {
        return ApiResponse.ok("OAuth 로그인 성공");
    }

    @GetMapping("/failure")
    public ApiResponse<String> oauth2Failure() {
        return ApiResponse.ok("OAuth 로그인 실패");
    }
}
