package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.result.NiceEncryptedDataResult;
import com.pintoss.gitftmall.domain.membership.domain.repository.NiceApiTokenRepository;
import com.pintoss.gitftmall.domain.membership.domain.service.NiceAuthHmacGenerator;
import com.pintoss.gitftmall.domain.membership.domain.service.NiceAuthCommandFactory;
import com.pintoss.gitftmall.domain.membership.domain.service.NiceAuthStandardAuthRequestEncryptor;
import com.pintoss.gitftmall.domain.membership.domain.service.NiceAuthSymmetricKeyGenerator;
import com.pintoss.gitftmall.domain.membership.domain.service.command.EncryptedRequestDataCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateHmacIntegrityCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.result.SymmetricKeyResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.config.NiceApiProperties;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiCryptoToken;
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
public class NiceAuthRequestService {

    private final NiceApiProperties niceApiProperties;
    private final NiceApiTokenRepository niceApiTokenRepository;
    private final NiceAuthCommandFactory niceAuthCommandFactory;
    private final NiceAuthSymmetricKeyGenerator niceAuthSymmetricKeyGenerator;
    private final NiceAuthStandardAuthRequestEncryptor niceAuthStandardAuthRequestEncryptor;
    private final NiceAuthHmacGenerator niceAuthHmacGenerator;

    public NiceEncryptedDataResult getEncryptedData() {
        niceApiTokenRepository.getAccessToken();

        NiceApiCryptoToken cryptoToken = niceApiTokenRepository.getCryptoToken();

        GenerateSymmetricKeyCommand symmetricKeyCommand = niceAuthCommandFactory.createSymmetricKeyCommand(cryptoToken);
        SymmetricKeyResult symmetricKey = niceAuthSymmetricKeyGenerator.generate(symmetricKeyCommand);

        EncryptedRequestDataCommand encryptedRequestDataCommand = niceAuthCommandFactory.createEncryptedRequestDataCommand(symmetricKey, cryptoToken, niceApiProperties.getRedirectUri());
        String encData = niceAuthStandardAuthRequestEncryptor.encryption(encryptedRequestDataCommand);

        GenerateHmacIntegrityCommand generateHmacIntegrityCommand = niceAuthCommandFactory.createHmacIntegrityCommand(symmetricKey, encData);
        String integrity = niceAuthHmacGenerator.generate(generateHmacIntegrityCommand);

        return new NiceEncryptedDataResult(
                cryptoToken.getTokenVersionId(),
                encData,
                integrity
        );
    }
}
