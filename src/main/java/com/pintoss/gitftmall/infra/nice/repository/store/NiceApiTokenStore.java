package com.pintoss.gitftmall.infra.nice.repository.store;

import com.pintoss.gitftmall.infra.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.infra.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.infra.nice.model.NiceApiSymmetricKey;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class NiceApiTokenStore {
    private NiceApiAccessToken accessToken;
    private NiceApiCryptoToken cryptoToken;
    private NiceApiSymmetricKey symmetricKey;
}
