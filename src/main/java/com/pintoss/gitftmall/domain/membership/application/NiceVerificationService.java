package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.domain.membership.application.command.NiceVerificationServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiAuthenticationEncryptorPort;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiAuthenticationHandlerPort;
import com.pintoss.gitftmall.domain.membership.application.port.NiceApiTokenManagerPort;
import com.pintoss.gitftmall.domain.membership.application.result.NiceVerificationServiceResult;
import com.pintoss.gitftmall.infra.external.nice.adapter.in.NiceApiAuthenticationEncryptorCommand;
import com.pintoss.gitftmall.infra.external.nice.client.response.NiceApiAuthenticationEncryptorResult;
import com.pintoss.gitftmall.infra.external.nice.model.NiceApiSymmetricKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NiceVerificationService {

    private final NiceApiTokenManagerPort niceApiTokenManagerPort;
    private final NiceApiAuthenticationEncryptorPort niceApiAuthenticationEncryptorPort;
    private final NiceApiAuthenticationHandlerPort niceApiAuthenticationHandlerPort;

    public NiceVerificationServiceResult verify(NiceVerificationServiceCommand command) {
        NiceApiSymmetricKey symmetricKey = niceApiTokenManagerPort.getSymmetricKey();

        NiceApiAuthenticationEncryptorCommand niceApiAuthenticationEncryptorCommand =
                new NiceApiAuthenticationEncryptorCommand(symmetricKey.getKey(), symmetricKey.getIv(),command.getEncData());

        NiceApiAuthenticationEncryptorResult niceApiAuthenticationEncryptorResult = niceApiAuthenticationEncryptorPort.encrypt(niceApiAuthenticationEncryptorCommand);

        if( niceApiAuthenticationHandlerPort.handler(niceApiAuthenticationEncryptorResult) ) {
            return new NiceVerificationServiceResult(false, null);
        }
        return new NiceVerificationServiceResult(true, niceApiAuthenticationEncryptorResult.getMobileno());
    }
}












//        try{
//            NiceApiSymmetricKey symmetricKey = niceApiTokenManagerPort.getSymmetricKey();
//            SecretKey secureKey = new SecretKeySpec(symmetricKey.getKey().getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(symmetricKey.getIv().getBytes()));
//
//            byte[] decodedBytes = Base64.getDecoder().decode(command.getEncData());
//            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
//            String json = new String(decryptedBytes, StandardCharsets.UTF_8);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            niceApiAuthenticationResult = objectMapper.readValue(json, NiceApiAuthenticationResult.class);
//
//            if(niceApiAuthenticationResult.getResultcode().equals("0000")){
//                niceApiAuthenticationResult.setIsSuccess(Boolean.TRUE);
//            }else{
//                niceApiAuthenticationResult.setIsSuccess(Boolean.FALSE);
//            }
//        } catch (Exception e) {
//            niceApiAuthenticationResult = new NiceApiAuthenticationResult();
//            niceApiAuthenticationResult.setIsSuccess(Boolean.FALSE);
//        }
//        return niceApiAuthenticationResult;
