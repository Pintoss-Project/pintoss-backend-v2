package com.pintoss.gitftmall.infra.nice.adapter.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceApiEncryptedRequestDataCommand {

    private String key;
    private String iv;
    private String reqNo;
    private String redirectUrl;
    private String siteCode;

    private NiceApiEncryptedRequestDataCommand(String key, String iv, String reqNo, String redirectUrl, String siteCode) {
        this.key = key;
        this.iv = iv;
        this.reqNo = reqNo;
        this.redirectUrl = redirectUrl;
        this.siteCode = siteCode;
    }

    public static NiceApiEncryptedRequestDataCommand create(String key, String iv, String reqNo, String redirectUrl, String siteCode) {
        return new NiceApiEncryptedRequestDataCommand(key, iv, reqNo, redirectUrl, siteCode);
    }
}
