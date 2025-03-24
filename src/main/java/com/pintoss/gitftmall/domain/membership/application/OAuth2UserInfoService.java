package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.dto.OAuth2UserInfoResponse;

public interface OAuth2UserInfoService {

    OAuth2UserInfoResponse getUserInfo(String code);

}
