package com.pintoss.gitftmall.domain.membership.infra.nice.persistence.repository.store;

import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiAccessToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiSymmetricKey;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class NiceApiTokenStore {
    private NiceApiAccessToken accessToken;
    private NiceApiCryptoToken cryptoToken;
    private NiceApiSymmetricKey symmetricKey;
}
