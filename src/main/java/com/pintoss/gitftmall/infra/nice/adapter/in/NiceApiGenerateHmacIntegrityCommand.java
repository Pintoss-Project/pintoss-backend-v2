package com.pintoss.gitftmall.infra.nice.adapter.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceApiGenerateHmacIntegrityCommand {

    private String hmacKey;
    private String encData;

    private NiceApiGenerateHmacIntegrityCommand(String hmacKey, String encData) {
        this.hmacKey = hmacKey;
        this.encData = encData;
    }

    public static NiceApiGenerateHmacIntegrityCommand create(String hmacKey, String encData) {
        return new NiceApiGenerateHmacIntegrityCommand(hmacKey, encData);
    }
}
