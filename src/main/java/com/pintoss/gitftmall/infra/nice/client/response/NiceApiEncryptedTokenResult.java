package com.pintoss.gitftmall.infra.nice.client.response;

import lombok.Data;

@Data
public class NiceApiEncryptedTokenResult {
    public NiceApiEncryptedTokenResultDataHeader dataHeader;
    public NiceApiEncryptedTokenResultDataBody dataBody;
}
