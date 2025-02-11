package com.pintoss.gitftmall.domain.membership.infra.nice.repository;

import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiSymmetricKey;
import com.pintoss.gitftmall.domain.membership.infra.nice.repository.store.NiceApiTokenStore;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NiceApiTokenRepository {
    private final NiceApiTokenStore store;

    public boolean isEmptyAccessToken() {
        return Objects.isNull(store.getAccessToken());
    }

    public boolean isExpiredAccessToken() {
        if(Objects.isNull(store.getAccessToken())) {
            return true;
        }
        return store.getAccessToken().isExpired();
    }

    public boolean isEmptyCryptoToken() {
        return Objects.isNull(store.getCryptoToken());
    }

    public boolean isExpiredCryptoToken() {
        if(Objects.isNull(store.getCryptoToken())) {
            return true;
        }
        return store.getCryptoToken().isExpired();
    }

    public NiceApiAccessToken getAccessToken() {
        return store.getAccessToken();
    }

    public NiceApiCryptoToken getCryptoToken() {
        return store.getCryptoToken();
    }

    public NiceApiSymmetricKey getSymmetricKey() {
        if(Objects.isNull(store.getSymmetricKey())) {
            return null;
        }
        return this.store.getSymmetricKey();
    }

    public void saveAccessToken(NiceApiAccessToken accessToken) {
        this.store.setAccessToken(accessToken);
        this.store.setCryptoToken(null);
        this.store.setSymmetricKey(null);
    }

    public void saveEncryptedToken(NiceApiCryptoToken cryptoToken) {
        this.store.setCryptoToken(cryptoToken);
        this.store.setSymmetricKey(null);
    }

    public void saveSymmetricKey(NiceApiSymmetricKey symmetricKey) {
        this.store.setSymmetricKey(symmetricKey);
    }
}
