package com.pintoss.gitftmall.infra.nice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pintoss.gitftmall.domain.membership.application.command.*;
import com.pintoss.gitftmall.domain.membership.application.result.EncryptedResult;
import com.pintoss.gitftmall.domain.membership.application.result.EncryptedTokenResponse;
import com.pintoss.gitftmall.infra.nice.client.NiceAuthClient;
import com.pintoss.gitftmall.infra.nice.config.NiceAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NiceAuthService {

    private final NiceAuthProperties niceAuthProperties;
    private final NiceAuthClient niceAuthClient;

    private String getAccessToken() {

        AccessTokenResult result = niceAuthClient.getAccessToken(
                niceAuthProperties.getAccessTokenUri(),
                niceAuthProperties.getClientId(),
                niceAuthProperties.getClientSecret()
        ).block();

        return result.getDataBody().getAccess_token();
    }

    @Transactional
    public EncryptedTokenResponse getEncryptedToken() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        EncryptedRequest encryptedRequest = new EncryptedRequest(
                new EncryptedRequestDataHeader("ko"),
                new EncryptedRequestDataBody(now.format(formatter), UUID.randomUUID().toString().substring(0,10), "1"));

        EncryptedResult response = niceAuthClient.getEncryptedToken(
                niceAuthProperties.getEncryptedTokenUri(),
                getAccessToken(),
                niceAuthProperties.getClientId(),
                niceAuthProperties.getProductId(),
                encryptedRequest
        ).block();


        String symmetricKey = encryptedRequest.getDataBody().req_dtim.trim() + encryptedRequest.getDataBody().req_no.trim() + response.dataBody.getToken_val().trim();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(symmetricKey.getBytes());
        byte[] arrHashValue = md.digest();
        String base64Encoded = Base64.getEncoder().encodeToString(arrHashValue);
        // 대칭키 (key): 앞에서 16바이트(32문자)
        String key = base64Encoded.substring(0, 16);
        // 초기 벡터 (iv): 뒤에서 16바이트(32문자)
        String iv = base64Encoded.substring(base64Encoded.length() - 16);
        // HMAC 키: 앞에서 32바이트(64문자)
        String hmacKey = base64Encoded.substring(0, 32);
//        System.out.println("base64Encoded = " + base64Encoded);

        EncryptedRequestData reqData = new EncryptedRequestData(
                encryptedRequest.getDataBody().req_no.trim(),
                niceAuthProperties.getRedirectUri(),
                response.getDataBody().getSite_code()
                );

        SecretKey secureKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(reqData);
        byte[] encrypted = cipher.doFinal(jsonString.getBytes());
        String enc_data = Base64.getEncoder().encodeToString(encrypted);
//        System.out.println(enc_data);
//        System.out.println(key);
//        System.out.println(iv);
//        System.out.println(hmacKey);

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(hmacKey.getBytes(), "HmacSHA256");
        mac.init(keySpec);
        byte[] hmac256 = mac.doFinal(enc_data.getBytes());
        String integrity = Base64.getEncoder().encodeToString(hmac256);

        return new EncryptedTokenResponse(
                response.getDataBody().getToken_version_id(),
                enc_data,
                integrity,
                key,
                iv,
                hmacKey
                );
    }
}