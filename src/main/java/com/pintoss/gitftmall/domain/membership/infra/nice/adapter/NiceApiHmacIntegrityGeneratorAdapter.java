package com.pintoss.gitftmall.domain.membership.infra.nice.adapter;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.server.InternalServerException;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiHmacIntegrityGeneratorPort;
import com.pintoss.gitftmall.domain.membership.infra.nice.adapter.in.NiceApiGenerateHmacIntegrityCommand;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class NiceApiHmacIntegrityGeneratorAdapter implements NiceApiHmacIntegrityGeneratorPort {
    public String generate(NiceApiGenerateHmacIntegrityCommand command){
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(ErrorCode.UNSUPPORTED_ENCRYPTION_ALGORITHM, "HMAC 무결성 체크 값 생성 중 문제가 발생했습니다.");
        }
        SecretKeySpec keySpec = new SecretKeySpec(command.getHmacKey().getBytes(), "HmacSHA256");
        try {
            mac.init(keySpec);
        } catch (InvalidKeyException e) {
            throw new InternalServerException(ErrorCode.INVALID_HMAC_KEY);
        }
        byte[] hmac256 = mac.doFinal(command.getEncData().getBytes());
        return Base64.getEncoder().encodeToString(hmac256);
    }
}
