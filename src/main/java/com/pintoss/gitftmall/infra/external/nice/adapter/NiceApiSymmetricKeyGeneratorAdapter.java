package com.pintoss.gitftmall.infra.external.nice.adapter;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.server.InternalServerException;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiSymmetricKeyGeneratorPort;
import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiGenerateSymmetricKeyCommand;
import com.pintoss.gitftmall.infra.external.nice.adapter.out.NiceApiSymmetricKeyResult;
import com.pintoss.gitftmall.infra.external.nice.model.NiceApiSymmetricKey;
import com.pintoss.gitftmall.infra.external.nice.repository.NiceApiTokenRepository;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class NiceApiSymmetricKeyGeneratorAdapter implements NiceApiSymmetricKeyGeneratorPort {

    private final NiceApiTokenRepository niceApiTokenRepository;

    public NiceApiSymmetricKeyGeneratorAdapter(NiceApiTokenRepository niceApiTokenRepository) {
        this.niceApiTokenRepository = niceApiTokenRepository;
    }

    @Override
    public NiceApiSymmetricKeyResult generate(NiceApiGenerateSymmetricKeyCommand command) {
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
        niceApiTokenRepository.saveSymmetricKey(symmetricKeyObj);

        return new NiceApiSymmetricKeyResult(key, iv, hmacKey);
    }
}
