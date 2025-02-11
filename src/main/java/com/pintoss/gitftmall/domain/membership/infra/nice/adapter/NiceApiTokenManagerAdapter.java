package com.pintoss.gitftmall.domain.membership.infra.nice.adapter;

import com.pintoss.gitftmall.domain.membership.application.port.NiceApiTokenManagerPort;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.NiceApiClient;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.request.NiceApiAccessTokenResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.request.NiceApiEncryptedTokenRequest;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.request.NiceApiEncryptedTokenRequestDataBody;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.request.NiceApiEncryptedTokenRequestDataHeader;
import com.pintoss.gitftmall.domain.membership.infra.nice.client.response.NiceApiEncryptedTokenResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.config.NiceApiProperties;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiAccessToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiCryptoToken;
import com.pintoss.gitftmall.domain.membership.infra.nice.model.NiceApiSymmetricKey;
import com.pintoss.gitftmall.domain.membership.infra.nice.repository.NiceApiTokenRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class NiceApiTokenManagerAdapter implements NiceApiTokenManagerPort {

    private final NiceApiProperties niceApiProperties;
    private final NiceApiClient niceApiClient;
    private final NiceApiTokenRepository niceApiTokenRepository;

    @Override
    public void saveSymmetricKey(NiceApiSymmetricKey symmetricKey1) {
        niceApiTokenRepository.saveSymmetricKey(symmetricKey1);
    }

    public NiceApiTokenManagerAdapter(
        NiceApiProperties niceApiProperties, NiceApiClient niceApiClient, NiceApiTokenRepository niceApiTokenRepository) {
        this.niceApiProperties = niceApiProperties;
        this.niceApiClient = niceApiClient;
        this.niceApiTokenRepository = niceApiTokenRepository;
    }

    @Override
    public NiceApiAccessToken getAccessToken() {
        if((niceApiTokenRepository.isEmptyAccessToken()) && niceApiTokenRepository.isExpiredAccessToken()){
            NiceApiAccessTokenResult response = niceApiClient.getAccessToken(
                    niceApiProperties.getAccessTokenUri(),
                    niceApiProperties.getClientId(),
                    niceApiProperties.getClientSecret()
            ).block();
            NiceApiAccessToken accessToken = new NiceApiAccessToken(
                    response.getDataBody().getAccess_token(),
                    LocalDateTime.now().plusSeconds(Long.parseLong(response.getDataBody().getExpires_in()))
            );
            niceApiTokenRepository.saveAccessToken(accessToken);
            return accessToken;
        }
        return niceApiTokenRepository.getAccessToken();
    }

    @Override
    public NiceApiCryptoToken getCryptoToken() {
        if(niceApiTokenRepository.isEmptyCryptoToken() && niceApiTokenRepository.isExpiredCryptoToken()){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            
            NiceApiEncryptedTokenRequest niceApiEncryptedTokenRequest = new NiceApiEncryptedTokenRequest(
                    new NiceApiEncryptedTokenRequestDataHeader("ko"),
                    new NiceApiEncryptedTokenRequestDataBody(now.format(formatter), UUID.randomUUID().toString().substring(0,10), "1"));

            NiceApiEncryptedTokenResult response = niceApiClient.getEncryptedToken(
                    niceApiProperties.getEncryptedTokenUri(),
                    niceApiTokenRepository.getAccessToken().getValue(),
                    niceApiProperties.getClientId(),
                    niceApiProperties.getProductId(),
                    niceApiEncryptedTokenRequest
            ).block();
            NiceApiCryptoToken cryptoToken = new NiceApiCryptoToken(
                    niceApiEncryptedTokenRequest.getDataBody().getReq_dtim(),
                    niceApiEncryptedTokenRequest.getDataBody().getReq_no(),
                    response.getDataBody().getSite_code(),
                    response.getDataBody().getToken_version_id(),
                    response.getDataBody().getToken_val(),
                    LocalDateTime.now().plusSeconds(response.getDataBody().getPeriod())
            );
            niceApiTokenRepository.saveEncryptedToken(cryptoToken);
            return cryptoToken;
        }
        return niceApiTokenRepository.getCryptoToken();
    }

    @Override
    public NiceApiSymmetricKey getSymmetricKey() {
        return niceApiTokenRepository.getSymmetricKey();
    }
}
