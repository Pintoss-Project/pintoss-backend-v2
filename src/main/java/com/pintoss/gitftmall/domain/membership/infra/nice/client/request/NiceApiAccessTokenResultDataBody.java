package com.pintoss.gitftmall.domain.membership.infra.nice.client.request;

import lombok.Data;

@Data
public class NiceApiAccessTokenResultDataBody {
    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
}
