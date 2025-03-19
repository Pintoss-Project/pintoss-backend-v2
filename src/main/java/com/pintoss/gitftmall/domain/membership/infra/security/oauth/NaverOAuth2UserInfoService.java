package com.pintoss.gitftmall.domain.membership.infra.security.oauth;

import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.NaverApiClient;
import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.NaverUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("naver")
@RequiredArgsConstructor
public class NaverOAuth2UserInfoService implements OAuth2UserInfoService {

    private final NaverApiClient naverApiClient;

    public OAuth2UserInfo getUserInfo(String code) {
        String accessToken = naverApiClient.getAccessToken(code);
        NaverUserInfoResponse naverUserInfoResponse = naverApiClient.getUserInfo(accessToken);

        return OAuth2UserInfo.from(naverUserInfoResponse);
    }
}
