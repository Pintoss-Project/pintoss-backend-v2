package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.voucher.application.VoucherRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voucher-providers/{provider_id}")
@RequiredArgsConstructor
public class AdminVoucherController {

    private final VoucherRegisterService voucherRegisterService;

    @PostMapping("/vouchers")
    public ApiResponse<Void> registerVoucher(
            @PathVariable("provider_id") Long providerId,
            @RequestBody @Valid VoucherRegisterRequest request
    ) {
        VoucherRegisterServiceCommand command = new VoucherRegisterServiceCommand(
                request.getName(),
                request.getPrice(),
                request.getStock()
        );

        voucherRegisterService.register(providerId, command);

        return ApiResponse.ok(null);
    }
}
