package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.external.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.infra.external.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.infra.external.nice.model.NiceApiSymmetricKey;

public interface NiceApiTokenManagerPort {
    NiceApiAccessToken getAccessToken();

    NiceApiCryptoToken getCryptoToken();

    NiceApiSymmetricKey getSymmetricKey();

    void saveSymmetricKey(NiceApiSymmetricKey symmetricKey1);
}
