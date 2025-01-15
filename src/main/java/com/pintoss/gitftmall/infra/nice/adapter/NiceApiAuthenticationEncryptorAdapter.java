package com.pintoss.gitftmall.infra.nice.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiAuthenticationEncryptorPort;
import com.pintoss.gitftmall.infra.nice.adapter.in.NiceApiAuthenticationEncryptorCommand;
import com.pintoss.gitftmall.infra.nice.client.response.NiceApiAuthenticationEncryptorResult;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class NiceApiAuthenticationEncryptorAdapter implements NiceApiAuthenticationEncryptorPort {
    @Override
    public NiceApiAuthenticationEncryptorResult encrypt(NiceApiAuthenticationEncryptorCommand command) {
        NiceApiAuthenticationEncryptorResult niceApiAuthenticationEncryptorResult;
        try{
            SecretKey secureKey = new SecretKeySpec(command.getKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(command.getIv().getBytes()));

            byte[] decodedBytes = Base64.getDecoder().decode(command.getEncData());
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            String json = new String(decryptedBytes, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            niceApiAuthenticationEncryptorResult = objectMapper.readValue(json, NiceApiAuthenticationEncryptorResult.class);
            return niceApiAuthenticationEncryptorResult;
        } catch (Exception e) {
            return null;
        }
    }
}
