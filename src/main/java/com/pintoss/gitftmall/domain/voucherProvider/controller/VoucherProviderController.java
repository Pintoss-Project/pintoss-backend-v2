package com.pintoss.gitftmall.domain.voucherProvider.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucherProvider.application.VoucherProviderRegisterService;
import com.pintoss.gitftmall.domain.voucherProvider.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucherProvider.controller.request.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.infra.config.web.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voucher-providers")
@RequiredArgsConstructor
public class VoucherProviderController {

    private final VoucherProviderRegisterService voucherProviderRegisterService;

    @PostMapping
    @AuthorizationRequired({ RoleEnum.ADMIN, RoleEnum.USER })
    public ApiResponse<Void> registerVoucherProvider(@RequestBody @Valid VoucherProviderRegisterRequest request){
        VoucherProviderRegisterServiceCommand command = new VoucherProviderRegisterServiceCommand(
                request.getName(),
                request.isPopular(),
                request.getCardDiscount(),
                request.getPhoneDiscount(),
                request.getHomePage(),
                request.getCsCenter(),
                request.getDescription(),
                request.getPublisher(),
                request.getLogoImageUrl(),
                request.getNote(),
                request.getIndex()
        );

        voucherProviderRegisterService.register(command);

        return ApiResponse.ok(null);
    }
}
