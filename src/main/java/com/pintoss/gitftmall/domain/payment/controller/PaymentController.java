package com.pintoss.gitftmall.domain.payment.controller;


import com.pintoss.gitftmall.core.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping("/callback")
    public ApiResponse<String> paymentCallback() {
        return ApiResponse.ok("success");
    }

}
