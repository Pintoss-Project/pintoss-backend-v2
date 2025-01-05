package com.pintoss.gitftmall.domain.membership.application.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResult {

    private String accessToken;
    private String refreshToken;

}
