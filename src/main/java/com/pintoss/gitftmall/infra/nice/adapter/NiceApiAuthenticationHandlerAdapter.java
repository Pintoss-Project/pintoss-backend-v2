package com.pintoss.gitftmall.infra.nice.adapter;

import com.pintoss.gitftmall.domain.membership.application.port.NiceApiAuthenticationHandlerPort;
import com.pintoss.gitftmall.infra.nice.client.response.NiceApiAuthenticationEncryptorResult;
import org.springframework.stereotype.Component;

@Component
public class NiceApiAuthenticationHandlerAdapter implements NiceApiAuthenticationHandlerPort {
    @Override
    public boolean handler(NiceApiAuthenticationEncryptorResult result) {
        if( result == null ) {
            return false;
        }
        if( !result.getMobileno().equals("0000")) {
            return false;
        }
        return true;
    }
}
