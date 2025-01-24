package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiGenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.infra.external.nice.adapter.out.NiceApiSymmetricKeyResult;

public interface NiceApiSymmetricKeyGeneratorPort {
    NiceApiSymmetricKeyResult generate(NiceApiGenerateSymmetricKeyCommand command);
}
