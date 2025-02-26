package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherProviderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class VoucherProviderController {

    private final VoucherProviderRegisterService voucherProviderRegisterService;
    private final VoucherProviderRepository voucherProviderRepository;

    @PostMapping
    @AuthorizationRequired({ RoleEnum.ADMIN, RoleEnum.USER })
    public ApiResponse<Void> registerVoucherProvider(@RequestBody @Valid VoucherProviderRegisterRequest request){
        VoucherProviderRegisterServiceCommand command = VoucherProviderRegisterServiceCommand.from(request);

        voucherProviderRegisterService.register(command);

        return ApiResponse.ok(null);
    }

    @GetMapping
    public ApiResponse<List<VoucherProviderListResponse>> findAll() {
        List<VoucherProviderListResponse> voucherProviders = voucherProviderRepository.findAll();
        return ApiResponse.ok(voucherProviders);
    }
}
