package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiAuthenticationEncryptorCommand;
import com.pintoss.gitftmall.infra.external.nice.client.response.NiceApiAuthenticationEncryptorResult;

public interface NiceApiAuthenticationEncryptorPort {

    NiceApiAuthenticationEncryptorResult encrypt(NiceApiAuthenticationEncryptorCommand command);
}
