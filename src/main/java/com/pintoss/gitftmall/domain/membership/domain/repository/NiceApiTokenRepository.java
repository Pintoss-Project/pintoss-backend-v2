package com.pintoss.gitftmall.domain.membership.domain.repository;

import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiAccessToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiSymmetricKey;

public interface NiceApiTokenRepository {
    NiceApiAccessToken getAccessToken();

    NiceApiCryptoToken getCryptoToken();

    NiceApiSymmetricKey getSymmetricKey();

}
