package com.pintoss.gitftmall.infra.external.nice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NiceApiCryptoToken {
    private String reqDtim;
    private String reqNo;
    private String siteCode;
    private String tokenVersionId;
    private String tokenValue;
    private LocalDateTime expiredAt;

    public boolean isExpired(){
        LocalDateTime now = LocalDateTime.now();
        if ( expiredAt.isBefore(now) ){
            return true;
        }
        return false;
    }
}
