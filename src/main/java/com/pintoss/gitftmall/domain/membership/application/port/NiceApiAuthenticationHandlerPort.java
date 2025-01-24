package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.external.nice.client.response.NiceApiAuthenticationEncryptorResult;

public interface NiceApiAuthenticationHandlerPort {

    boolean handler(NiceApiAuthenticationEncryptorResult result);
}
