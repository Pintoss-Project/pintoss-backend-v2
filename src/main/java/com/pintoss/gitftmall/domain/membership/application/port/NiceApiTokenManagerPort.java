package com.pintoss.gitftmall.domain.membership.application.port;

import com.pintoss.gitftmall.infra.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.infra.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.infra.nice.model.NiceApiSymmetricKey;

public interface NiceApiTokenManagerPort {
    NiceApiAccessToken getAccessToken();

    NiceApiCryptoToken getCryptoToken();

    NiceApiSymmetricKey getSymmetricKey();

    void saveSymmetricKey(NiceApiSymmetricKey symmetricKey1);
}
