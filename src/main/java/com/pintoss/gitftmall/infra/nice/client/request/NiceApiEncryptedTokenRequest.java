package com.pintoss.gitftmall.infra.nice.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NiceApiEncryptedTokenRequest {
    public NiceApiEncryptedTokenRequestDataHeader dataHeader;
    public NiceApiEncryptedTokenRequestDataBody dataBody;
}
