package com.pintoss.gitftmall.domain.voucherProvider.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.voucherProvider.application.VoucherProviderRegisterService;
import com.pintoss.gitftmall.domain.voucherProvider.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucherProvider.controller.request.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.domain.repository.IVoucherProviderRepository;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import java.util.List;
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
    private final IVoucherProviderRepository voucherProviderRepository;

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

    @GetMapping
    public ApiResponse<List<VoucherProviderListResponse>> findAll() {
        List<VoucherProviderListResponse> voucherProviders = voucherProviderRepository.findAll();
        return ApiResponse.ok(voucherProviders);
    }
}
