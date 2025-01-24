package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voucher-providers")
@RequiredArgsConstructor
public class AdminVoucherProviderController {

    private final VoucherProviderRegisterService voucherProviderRegisterService;

    @PostMapping
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> registerVoucherProvider(@RequestBody @Valid VoucherProviderRegisterRequest request){
        VoucherProviderRegisterServiceCommand command = new VoucherProviderRegisterServiceCommand(
                request.getName(),
                request.isPopular(),
                request.getHomePage(),
                request.getCsCenter(),
                request.getCardDiscount(),
                request.getPhoneDiscount(),
                request.getDescription(),
                request.getPublisher(),
                request.getValidatedCategory(),
                request.getLogoImageUrl(),
                request.getNote(),
                request.getIndex()
        );

        voucherProviderRegisterService.register(command);

        return ApiResponse.ok(null);
    }
}
