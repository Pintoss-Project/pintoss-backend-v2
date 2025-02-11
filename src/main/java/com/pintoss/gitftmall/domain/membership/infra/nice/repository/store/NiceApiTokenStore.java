package com.pintoss.gitftmall.domain.membership.infra.nice.repository.store;

import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiSymmetricKey;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class NiceApiTokenStore {
    private NiceApiAccessToken accessToken;
    private NiceApiCryptoToken cryptoToken;
    private NiceApiSymmetricKey symmetricKey;
}
