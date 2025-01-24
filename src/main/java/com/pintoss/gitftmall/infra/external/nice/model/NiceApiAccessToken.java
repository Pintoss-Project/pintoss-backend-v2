package com.pintoss.gitftmall.infra.external.nice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NiceApiAccessToken {

    private String value;

    private LocalDateTime expiredAt;

    public boolean isExpired(){
        LocalDateTime now = LocalDateTime.now();
        if ( expiredAt.isBefore(now) ){
            return true;
        }
        return false;
    }
}
