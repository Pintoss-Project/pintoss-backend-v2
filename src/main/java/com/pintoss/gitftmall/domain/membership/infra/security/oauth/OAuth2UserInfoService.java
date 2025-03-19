package com.pintoss.gitftmall.domain.membership.infra.security.oauth;

public interface OAuth2UserInfoService {

    OAuth2UserInfo getUserInfo(String code);

}
