package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.NiceAuthRequestService;
import com.pintoss.gitftmall.domain.membership.application.NiceAuthVerificationService;
import com.pintoss.gitftmall.domain.membership.application.command.NiceVerificationServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.NiceEncryptedDataResult;
import com.pintoss.gitftmall.domain.membership.application.result.NiceVerificationResult;
import com.pintoss.gitftmall.domain.membership.controller.response.EncryptedDataResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/nice")
@RequiredArgsConstructor
public class NiceAuthController {

    private final NiceAuthRequestService niceAuthRequestService;
    private final NiceAuthVerificationService niceAuthVerificationService;

    @GetMapping("/encrypted-data")
    public ApiResponse<EncryptedDataResponse> getEncryptedData() {
        NiceEncryptedDataResult encRequestData = niceAuthRequestService.getEncryptedData();
        return ApiResponse.ok(new EncryptedDataResponse(encRequestData.getToken_version_id(), encRequestData.getEnc_data(), encRequestData.getIntegrity_value()));
    }

//    @GetMapping("/callback")
//    public ApiResponse<VerifyResponse> niceCallback(
//            @RequestParam(name = "token_version_id") String tokenVersionId,
//            @RequestParam(name = "enc_data") String encData,
//            @RequestParam(name = "integrity_value") String integrityValue
//    ) {
//        NiceVerificationServiceCommand command = new NiceVerificationServiceCommand(tokenVersionId, encData, integrityValue);
//
//        NiceVerificationResult verifyResult = niceAuthVerificationService.verify(command);
//
//        return ApiResponse.ok(VerifyResponse.from(verifyResult));
//    }
    @GetMapping("/callback")
    public void niceCallback(
            @RequestParam(name = "token_version_id") String tokenVersionId,
            @RequestParam(name = "enc_data") String encData,
            @RequestParam(name = "integrity_value") String integrityValue,
            HttpServletResponse response
    ) throws IOException {
        NiceVerificationServiceCommand command = new NiceVerificationServiceCommand(tokenVersionId, encData, integrityValue);

        NiceVerificationResult verifyResult = niceAuthVerificationService.verify(command);
        String redirectUrl = String.format("https://pin-toss.com/register/nice?name=%s&tel=%s&success=%s", URLEncoder.encode(verifyResult.getName(), StandardCharsets.UTF_8.toString()), verifyResult.getTel(), verifyResult.getIsSuccess());
        response.sendRedirect(redirectUrl);
    }
}
