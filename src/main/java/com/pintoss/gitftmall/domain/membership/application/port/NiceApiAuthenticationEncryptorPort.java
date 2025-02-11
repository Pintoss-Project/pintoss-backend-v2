package com.pintoss.gitftmall.domain.membership.application.port;


import com.pintoss.gitftmall.domain.membership.infra.nice.adapter.in.NiceApiAuthenticationEncryptorCommand;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.response.NiceApiAuthenticationEncryptorResult;

public interface NiceApiAuthenticationEncryptorPort {

    NiceApiAuthenticationEncryptorResult encrypt(NiceApiAuthenticationEncryptorCommand command);
}
