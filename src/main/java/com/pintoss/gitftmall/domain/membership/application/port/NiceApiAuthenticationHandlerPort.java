package com.pintoss.gitftmall.domain.membership.application.port;


import com.pintoss.gitftmall.domain.membership.infra.nice.client.response.NiceApiAuthenticationEncryptorResult;

public interface NiceApiAuthenticationHandlerPort {

    boolean handler(NiceApiAuthenticationEncryptorResult result);
}
