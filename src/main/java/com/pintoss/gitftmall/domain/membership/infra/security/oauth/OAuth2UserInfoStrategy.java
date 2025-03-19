package com.pintoss.gitftmall.domain.membership.infra.security.oauth;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.BadRequestException;
import com.pintoss.gitftmall.domain.membership.domain.vo.LoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2UserInfoStrategy {

    private final Map<String, OAuth2UserInfoService> oAuth2UserInfoServiceMap;

    public OAuth2UserInfoService getOAuth2UserInfoService(LoginType providerType) {
        OAuth2UserInfoService service = oAuth2UserInfoServiceMap.get(providerType.getValue());

        if(service == null) {
            throw new BadRequestException(ErrorCode.UNSUPPORTED_OAUTH2_PROVIDER);
        }

        return service;
    }

}
