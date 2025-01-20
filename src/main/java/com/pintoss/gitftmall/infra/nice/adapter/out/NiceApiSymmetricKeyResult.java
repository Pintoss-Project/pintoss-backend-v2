package com.pintoss.gitftmall.infra.nice.adapter.out;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceApiSymmetricKeyResult {
    private String key;
    private String iv;
    private String hmacKey;

    public NiceApiSymmetricKeyResult(String key, String iv, String hmacKey) {
        this.key = key;
        this.iv = iv;
        this.hmacKey = hmacKey;
    }
}
