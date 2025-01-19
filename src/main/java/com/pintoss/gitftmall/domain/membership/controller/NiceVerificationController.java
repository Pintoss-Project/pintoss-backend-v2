package com.pintoss.gitftmall.domain.membership.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.application.NiceRequestService;
import com.pintoss.gitftmall.domain.membership.application.NiceVerificationService;
import com.pintoss.gitftmall.domain.membership.application.command.NiceVerificationServiceCommand;
import com.pintoss.gitftmall.domain.membership.application.result.EncryptedDataResult;
import com.pintoss.gitftmall.domain.membership.application.result.NiceVerificationServiceResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nice")
@RequiredArgsConstructor
public class NiceVerificationController {

    private final NiceRequestService niceRequestService;
    private final NiceVerificationService niceVerificationService;

    @GetMapping("/request")
    public ApiResponse<EncryptedDataResult> callNiceApi() {
        EncryptedDataResult encRequestData = niceRequestService.callNiceApi();
        return ApiResponse.ok(encRequestData);
    }

    @GetMapping("/callback")
    public ApiResponse<Void> niceCallback(
            @RequestParam(name = "token_version_id") String tokenVersionId,
            @RequestParam(name = "enc_data") String encData,
            @RequestParam(name = "integrity_value") String integrityValue,
            HttpServletResponse servletResponse
    ) throws Exception{
        NiceVerificationServiceCommand command = new NiceVerificationServiceCommand(tokenVersionId, encData, integrityValue);

        NiceVerificationServiceResult verifyResult = niceVerificationService.verify(command);

        if(verifyResult.getIsSuccess()){
            servletResponse.sendRedirect(String.format("http://localhost:5173/nice/callback?isSuccess=%s&tel=%s", verifyResult.getIsSuccess(),verifyResult.getTel()));
            return ApiResponse.ok(null);
        }
        servletResponse.sendRedirect(String.format("http://localhost:5173/nice/callback?isSuccess=%s",verifyResult.getIsSuccess()));
        return ApiResponse.ok(null);
    }
}
