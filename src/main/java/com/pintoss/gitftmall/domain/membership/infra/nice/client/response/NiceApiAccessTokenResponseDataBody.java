package com.pintoss.gitftmall.domain.membership.infra.nice.client.response;

import lombok.Data;

@Data
public class NiceApiAccessTokenResponseDataBody {
    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
}
