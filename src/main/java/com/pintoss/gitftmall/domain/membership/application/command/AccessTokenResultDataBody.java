package com.pintoss.gitftmall.domain.membership.application.command;

import lombok.Data;

@Data
public class AccessTokenResultDataBody {
    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
}
