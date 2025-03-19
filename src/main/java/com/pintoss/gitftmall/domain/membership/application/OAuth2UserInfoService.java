package com.pintoss.gitftmall.domain.membership.application;

public interface OAuth2UserInfoService {

    OAuth2UserInfoResponse getUserInfo(String code);

}
