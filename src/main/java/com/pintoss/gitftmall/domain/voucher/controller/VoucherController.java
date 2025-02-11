package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherRegisterRequest;
import com.pintoss.gitftmall.core.config.web.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherRegisterService voucherRegisterService;

    @PostMapping()
    @AuthorizationRequired({RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<Void> registerVoucher(@RequestBody @Valid VoucherRegisterRequest request) {
        VoucherRegisterServiceCommand command = new VoucherRegisterServiceCommand(
                request.getVoucherProviderId(),
                request.getPrice(),
                request.getStock()
        );

        voucherRegisterService.register(command);

        return ApiResponse.ok(null);
    }
}
