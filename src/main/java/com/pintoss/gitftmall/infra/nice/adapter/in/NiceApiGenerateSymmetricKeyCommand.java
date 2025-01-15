package com.pintoss.gitftmall.infra.nice.adapter.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceApiGenerateSymmetricKeyCommand {
    private String reqDtim;
    private String reqNo;
    private String tokenValue;


    private NiceApiGenerateSymmetricKeyCommand(String reqDtim, String reqNo, String tokenValue) {
        this.reqDtim = reqDtim;
        this.reqNo = reqNo;
        this.tokenValue = tokenValue;
    }

    public static NiceApiGenerateSymmetricKeyCommand create(String reqDtim, String reqNo, String tokenValue) {
        return new NiceApiGenerateSymmetricKeyCommand(reqDtim, reqNo, tokenValue);
    }
}
