package com.pintoss.gitftmall.domain.membership.infra.nice.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceApiCryptoTokenRequest {
    public NiceApiCryptoTokenRequestDataHeader dataHeader;
    public NiceApiCryptoTokenRequestDataBody dataBody;
}
