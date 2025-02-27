package com.pintoss.gitftmall.domain.payment.controller;


import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.payment.application.PaymentAuthCallbackService;
import com.pintoss.gitftmall.domain.payment.application.dto.PaymentAuthCallbackServiceCommand;
import com.pintoss.gitftmall.domain.payment.controller.dto.PaymentAuthCallbackRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentAuthCallbackService paymentAuthCallbackService;

    @PostMapping("/callback")
    public ApiResponse<String> paymentCallback(@ModelAttribute PaymentAuthCallbackRequest request) {
        PaymentAuthCallbackServiceCommand command = PaymentAuthCallbackServiceCommand.from(request);
        // TODO : 사진과 같이 요청을 받는데
        paymentAuthCallbackService.auth(command);

        return ApiResponse.ok(command.getOrderId());
    }
}
