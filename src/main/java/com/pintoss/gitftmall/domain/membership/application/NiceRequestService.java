package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.port.NiceApiHmacIntegrityGeneratorPort;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiRequestDataEncryptionPort;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiSymmetricKeyGeneratorPort;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiTokenManagerPort;
import com.pintoss.gitftmall.domain.membership.application.result.EncryptedDataResult;
import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiEncryptedRequestDataCommand;
import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiGenerateHmacIntegrityCommand;
import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiGenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.infra.external.nice.adapter.out.NiceApiSymmetricKeyResult;
import com.pintoss.gitftmall.infra.external.nice.config.NiceApiProperties;
import com.pintoss.gitftmall.infra.external.nice.model.NiceApiCryptoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

 /*
     프로세스 순서
     1. 액세스 토큰 존재 여부 확인
          1-1. 존재하면 조회
          1-2. 없으면 요청하고 암호화 토큰도 재 요청
              1-2-1. 모든 토큰 생성 및 다른 토큰 삭제
     2. 존재하는 경우 암호화 토큰 존재 여부 확인
          2-1. 존재하면 조회
          2-2. 없으면 액세스 토큰 조회를 가지고 암호화 토큰 API 요청
              2-2-1. 모든 토큰 생성 및 다른 토큰 삭제
     3. 대칭키 생성
     4. 요청 데이터 암호화
     5. 무결성 체크
 */
@Service
@RequiredArgsConstructor
public class NiceRequestService {

    private final NiceApiProperties niceApiProperties;
    private final NiceApiTokenManagerPort niceApiTokenManagerPort;
    private final NiceApiSymmetricKeyGeneratorPort niceApiSymmetricKeyGeneratorPort;
    private final NiceApiRequestDataEncryptionPort niceApiRequestDataEncryptionPort;
    private final NiceApiHmacIntegrityGeneratorPort niceApiHmacIntegrityGeneratorPort;

    
    public EncryptedDataResult callNiceApi() {
        niceApiTokenManagerPort.getAccessToken();

        NiceApiCryptoToken cryptoToken = niceApiTokenManagerPort.getCryptoToken();

        NiceApiGenerateSymmetricKeyCommand niceApiGenerateSymmetricKeyCommand = NiceApiGenerateSymmetricKeyCommand.create(
                cryptoToken.getReqDtim(),
                cryptoToken.getReqNo(),
                cryptoToken.getTokenValue()
        );
        NiceApiSymmetricKeyResult symmetricKey = niceApiSymmetricKeyGeneratorPort.generate(niceApiGenerateSymmetricKeyCommand);

        NiceApiEncryptedRequestDataCommand niceApiEncryptedRequestDataCommand = NiceApiEncryptedRequestDataCommand.create(
                symmetricKey.getKey(),
                symmetricKey.getIv(),
                        cryptoToken.getReqNo(),
                niceApiProperties.getRedirectUri(),
                cryptoToken.getSiteCode()
        );
        String encData = niceApiRequestDataEncryptionPort.encryption(niceApiEncryptedRequestDataCommand);

        NiceApiGenerateHmacIntegrityCommand niceApiGenerateHmacIntegrityCommand = NiceApiGenerateHmacIntegrityCommand.create(
                symmetricKey.getHmacKey(),
                encData
        );
        String integrity = niceApiHmacIntegrityGeneratorPort.generate(niceApiGenerateHmacIntegrityCommand);

        return new EncryptedDataResult(
                cryptoToken.getTokenVersionId(),
                encData,
                integrity
        );
    }
}
