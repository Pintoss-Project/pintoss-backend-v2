package com.pintoss.gitftmall.infra.external.nice.client.response;

import lombok.Data;

@Data
public class NiceApiEncryptedTokenResult {
    public NiceApiEncryptedTokenResultDataHeader dataHeader;
    public NiceApiEncryptedTokenResultDataBody dataBody;
}
