package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.command.NiceVerificationServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.NiceVerificationResult;
import com.pintoss.gitftmall.domain.membership.domain.repository.NiceApiTokenRepository;
import com.pintoss.gitftmall.domain.membership.infra.nice.decipher.NiceAuthAuthenticationResponseDecipher;
import com.pintoss.gitftmall.domain.membership.infra.nice.decipher.NiceAuthAuthenticationHandler;
import com.pintoss.gitftmall.domain.membership.infra.nice.util.NiceAuthCommandFactory;
import com.pintoss.gitftmall.domain.membership.domain.service.command.AuthenticationDataDecipherCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.result.AuthenticationDataDecipherResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiSymmetricKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class NiceAuthVerificationService {

    private final NiceAuthCommandFactory niceAuthCommandFactory;
    private final NiceApiTokenRepository niceApiTokenRepository;
    private final NiceAuthAuthenticationResponseDecipher niceAuthAuthenticationResponseDeCipher;
    private final NiceAuthAuthenticationHandler niceAuthAuthenticationHandler;

    public NiceVerificationResult verify(NiceVerificationServiceCommand command) {
        NiceApiSymmetricKey symmetricKey = niceApiTokenRepository.getSymmetricKey();

        AuthenticationDataDecipherCommand authenticationDataDecipherCommand = niceAuthCommandFactory.createAuthenticationDataEncryptor(symmetricKey.getKey(), symmetricKey.getIv(), command.getEncData());
        AuthenticationDataDecipherResult authenticationDataDecipherResult = niceAuthAuthenticationResponseDeCipher.decipher(authenticationDataDecipherCommand);

        if( niceAuthAuthenticationHandler.handler(authenticationDataDecipherResult) ) {
            return new NiceVerificationResult(false, null, null);
        }
        return new NiceVerificationResult(true, URLDecoder.decode(authenticationDataDecipherResult.getUtf8_name(), StandardCharsets.UTF_8), authenticationDataDecipherResult.getMobileno());
    }
}
