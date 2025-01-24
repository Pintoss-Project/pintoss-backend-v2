package com.pintoss.gitftmall.infra.external.nice.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceApiEncryptedTokenRequest {
    public NiceApiEncryptedTokenRequestDataHeader dataHeader;
    public NiceApiEncryptedTokenRequestDataBody dataBody;
}
