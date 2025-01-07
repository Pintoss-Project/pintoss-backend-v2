package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2/authorization")
public class OAuthController {

    @GetMapping("/kakao")
    public ApiResponse<Void> oauth2Kakao() {

        return ApiResponse.ok(null);
    }

    @GetMapping("/naver")
    public ApiResponse<Void> oauth2Naver() {

        return ApiResponse.ok(null);
    }
}
