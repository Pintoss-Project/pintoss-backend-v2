package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.domain.membership.application.OAuth2Service;
import com.pintoss.gitftmall.domain.membership.domain.vo.OAuth2ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/login")
    public ResponseEntity<Void> getOAuth2LoginUrl(@RequestParam OAuth2ProviderType providerType) {

        String loginUrl = oAuth2Service.getOAuth2LoginUrl(providerType);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(loginUrl))
                .build();
    }
}
