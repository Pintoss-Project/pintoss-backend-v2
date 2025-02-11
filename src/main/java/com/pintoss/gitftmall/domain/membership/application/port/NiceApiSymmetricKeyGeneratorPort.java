package com.pintoss.gitftmall.domain.membership.application.port;


import com.pintoss.gitftmall.domain.membership.infra.nice.adapter.in.NiceApiGenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.domain.membership.infra.nice.adapter.out.NiceApiSymmetricKeyResult;

public interface NiceApiSymmetricKeyGeneratorPort {
    NiceApiSymmetricKeyResult generate(NiceApiGenerateSymmetricKeyCommand command);
}
