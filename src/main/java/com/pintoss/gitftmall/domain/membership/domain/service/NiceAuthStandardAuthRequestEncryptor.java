package com.pintoss.gitftmall.domain.membership.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.server.InternalServerException;
import com.pintoss.gitftmall.domain.membership.domain.service.command.EncryptedRequestDataCommand;
import com.pintoss.gitftmall.domain.membership.domain.vo.NiceAuthStandardAuthRequest;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class NiceAuthStandardAuthRequestEncryptor {

    public String encryption(EncryptedRequestDataCommand command) {
        SecretKey secureKey = new SecretKeySpec(command.getKey().getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(ErrorCode.UNSUPPORTED_ENCRYPTION_ALGORITHM, "요청 데이터 암호화 중 문제가 발생했습니다.");
        } catch (NoSuchPaddingException e) {
            throw new InternalServerException(ErrorCode.UNSUPPORTED_PADDING_SCHEMA, "요청 데이터 암호화 중 문제가 발생했습니다.");
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(command.getIv().getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeyException e) {
            throw new InternalServerException(ErrorCode.INVALID_ENCRYPTION_KEY, "요청 데이터 암호화 중 문제가 발생했습니다.");
        } catch (InvalidAlgorithmParameterException e) {
            throw new InternalServerException(ErrorCode.INVALID_ALGORITHM_PARAMETER, "요청 데이터 암호화 중 문제가 발생했습니다.");
        }
        ObjectMapper objectMapper = new ObjectMapper();

        NiceAuthStandardAuthRequest reqData = new NiceAuthStandardAuthRequest(
                command.getReqNo().trim(),
                command.getRedirectUrl(),
                command.getSiteCode()
        );
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(reqData);
        } catch (JsonProcessingException e) {
            throw new InternalServerException(ErrorCode.JSON_SERIALIZATION, "NICE API 응답 값을 JSON 형태로 파싱할 수 없습니다.");
        }
        byte[] encrypted = null;
        try {
            encrypted = cipher.doFinal(jsonString.getBytes());
        } catch (IllegalBlockSizeException e) {
            throw new InternalServerException(ErrorCode.ILLEGAL_BLOCK_SIZE);
        } catch (BadPaddingException e) {
            throw new InternalServerException(ErrorCode.BAD_PADDING);
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
