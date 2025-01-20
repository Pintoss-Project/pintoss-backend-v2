package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.nice.adapter.in.NiceApiGenerateHmacIntegrityCommand;

public interface NiceApiHmacIntegrityGeneratorPort {
    String generate(NiceApiGenerateHmacIntegrityCommand command);
}
