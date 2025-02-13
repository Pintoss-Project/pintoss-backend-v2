package com.pintoss.gitftmall.domain.membership.infra.nice.util;

import com.pintoss.gitftmall.domain.membership.domain.service.command.AuthenticationDataDecipherCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.command.EncryptedRequestDataCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateHmacIntegrityCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.result.SymmetricKeyResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiCryptoToken;
import org.springframework.stereotype.Component;

@Component
public class NiceAuthCommandFactory {
    public GenerateSymmetricKeyCommand createSymmetricKeyCommand(NiceApiCryptoToken cryptoToken) {
        return GenerateSymmetricKeyCommand.create(
                cryptoToken.getReqDtim(),
                cryptoToken.getReqNo(),
                cryptoToken.getTokenValue()
        );
    }

    public EncryptedRequestDataCommand createEncryptedRequestDataCommand(SymmetricKeyResult symmetricKey, NiceApiCryptoToken cryptoToken, String redirectUri) {
        return EncryptedRequestDataCommand.create(
                symmetricKey.getKey(),
                symmetricKey.getIv(),
                cryptoToken.getReqNo(),
                redirectUri,
                cryptoToken.getSiteCode()
        );
    }

    public GenerateHmacIntegrityCommand createHmacIntegrityCommand(SymmetricKeyResult symmetricKey, String encData) {
        return GenerateHmacIntegrityCommand.create(
                symmetricKey.getHmacKey(),
                encData
        );
    }

    public AuthenticationDataDecipherCommand createAuthenticationDataEncryptor(String key, String iv, String encData) {
        return AuthenticationDataDecipherCommand.create(key, iv, encData);
    }
}
