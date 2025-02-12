package com.pintoss.gitftmall.domain.membership.infra.nice.client.response;

import lombok.Data;

@Data
public class NiceApiAccessTokenResponse {
    private NiceApiAccessTokenResponseDataHeader dataHeader;
    private NiceApiAccessTokenResponseDataBody dataBody;
}
