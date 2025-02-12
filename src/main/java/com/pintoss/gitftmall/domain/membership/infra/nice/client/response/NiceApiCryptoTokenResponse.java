package com.pintoss.gitftmall.domain.membership.infra.nice.client.response;

import lombok.Data;

@Data
public class NiceApiCryptoTokenResponse {
    public NiceApiCryptoTokenResponseDataHeader dataHeader;
    public NiceApiCryptoTokenResponseDataBody dataBody;
}
