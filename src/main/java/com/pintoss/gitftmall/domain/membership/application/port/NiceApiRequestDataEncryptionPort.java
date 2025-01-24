package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiEncryptedRequestDataCommand;

public interface NiceApiRequestDataEncryptionPort {
    String encryption(NiceApiEncryptedRequestDataCommand command);
}
