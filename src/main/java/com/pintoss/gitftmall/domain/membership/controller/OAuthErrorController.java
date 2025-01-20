package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/oauth")
public class OAuthErrorController {

    @GetMapping("/error")
    public ApiResponse<String> handleOAuthError(@RequestParam(value = "message", required = false) String encodedMessage) {
        String decodedMessage = URLDecoder.decode(encodedMessage, StandardCharsets.UTF_8);

        return ApiResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, decodedMessage);
    }
}
