package com.pintoss.gitftmall.domain.membership.infra.security.oauth;

import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.KakaoUserInfoResponse;
import com.pintoss.gitftmall.domain.membership.infra.security.oauth.client.dto.NaverUserInfoResponse;
import lombok.Getter;

@Getter
public class OAuth2UserInfo {

    private String id;

    private String email;

    private String phone;

    private OAuth2UserInfo(String id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public static OAuth2UserInfo from(NaverUserInfoResponse res) {
        return new OAuth2UserInfo(res.getResponse().getId(), res.getResponse().getEmail(), res.getResponse().getMobile());
    }

    public static OAuth2UserInfo from(KakaoUserInfoResponse res) {
        return new OAuth2UserInfo(res.getId(), res.getKakaoAccount().getEmail(), null);
    }
}
