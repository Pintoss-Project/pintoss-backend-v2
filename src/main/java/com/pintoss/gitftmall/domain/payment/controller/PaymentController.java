package com.pintoss.gitftmall.domain.payment.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    @GetMapping
    public ApiResponse<Void> register(){
        return ApiResponse.ok(null);
    }

}
