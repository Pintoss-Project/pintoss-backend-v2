package com.pintoss.gitftmall.domain.membership.domain.service;

import com.pintoss.gitftmall.domain.membership.domain.service.result.AuthenticationDataDecipherResult;
import org.springframework.stereotype.Component;

@Component
public class NiceAuthAuthenticationHandler {
    public boolean handler(AuthenticationDataDecipherResult result) {
        if( result == null ) {
            return false;
        }
        if( !result.getMobileno().equals("0000")) {
            return false;
        }
        return true;
    }
}
