package com.pintoss.gitftmall.domain.membership.infra.nice.encryption;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.server.InternalServerException;
import com.pintoss.gitftmall.domain.membership.domain.service.command.GenerateHmacIntegrityCommand;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class NiceAuthHmacGenerator {

    public String generate(GenerateHmacIntegrityCommand command){
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
