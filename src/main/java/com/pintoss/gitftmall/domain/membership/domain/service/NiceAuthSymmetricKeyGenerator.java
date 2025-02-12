package com.pintoss.gitftmall.domain.membership.domain.service;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.server.InternalServerException;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.domain.membership.domain.service.result.SymmetricKeyResult;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.model.NiceApiSymmetricKey;
import com.pintoss.gitftmall.domain.membership.infra.nice.persistence.repository.NiceApiTokenRepositoryImpl;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class NiceAuthSymmetricKeyGenerator {

    private final NiceApiTokenRepositoryImpl niceApiTokenRepositoryImpl;

    public NiceAuthSymmetricKeyGenerator(NiceApiTokenRepositoryImpl niceApiTokenRepositoryImpl) {
        this.niceApiTokenRepositoryImpl = niceApiTokenRepositoryImpl;
    }

    public SymmetricKeyResult generate(GenerateSymmetricKeyCommand command) {
        String symmetricKey = command.getReqDtim().trim() + command.getReqNo() + command.getTokenValue();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(ErrorCode.UNSUPPORTED_ENCRYPTION_ALGORITHM, "대칭키 생성 중 에러가 발생했습니다.");
        }
        md.update(symmetricKey.getBytes());
        byte[] arrHashValue = md.digest();
        String base64Encoded = Base64.getEncoder().encodeToString(arrHashValue);

        String key = base64Encoded.substring(0, 16);
        String iv = base64Encoded.substring(base64Encoded.length() - 16);
        String hmacKey = base64Encoded.substring(0, 32);

        NiceApiSymmetricKey symmetricKeyObj = new NiceApiSymmetricKey(key, iv);
        niceApiTokenRepositoryImpl.saveSymmetricKey(symmetricKeyObj);

        return new SymmetricKeyResult(key, iv, hmacKey);
    }
}
