package com.pintoss.gitftmall.domain.membership.infra.security.oauth;

import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.KakaoApiClient;
import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.KakaoTokenResponse;
import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("kakao")
@RequiredArgsConstructor
public class KakaoOAuth2UserInfoService implements OAuth2UserInfoService {

    private final KakaoApiClient kakaoApiClient;

    public OAuth2UserInfo getUserInfo(String code) {
        KakaoTokenResponse tokenResponse = kakaoApiClient.getAccessToken(code);
        KakaoUserInfoResponse kakaoUserInfoResponse = kakaoApiClient.getUserInfo(tokenResponse.getAccessToken());

        return OAuth2UserInfo.from(kakaoUserInfoResponse);
    }
}
