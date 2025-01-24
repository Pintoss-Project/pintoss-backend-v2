package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderService;
import com.pintoss.gitftmall.domain.voucher.application.VoucherRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherProviderRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherProviderUpdateRequest;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherRegisterRequest;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voucher-providers")
@RequiredArgsConstructor
public class AdminVoucherProviderController {

    private final VoucherProviderRegisterService voucherProviderRegisterService;
    private final VoucherRegisterService voucherRegisterService;

    private final VoucherProviderService voucherProviderService;

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

        return new ApiResponse<>(HttpStatus.OK, "상품권 제조사 추가 완료", null);
    }

    @PostMapping("/{provider_id}/vouchers")
    @AuthorizationRequired(RoleEnum.ADMIN)
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

        return new ApiResponse<>(HttpStatus.OK, "상품권 추가 완료", null);
    }

    @PatchMapping("/{provider_id}")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> updateVoucherProvider(
            @PathVariable("provider_id") Long providerId,
            @RequestBody @Valid VoucherProviderUpdateRequest request
    ) {
        voucherProviderService.update(providerId, request);

        return new ApiResponse<>(HttpStatus.OK, "상품권 제조사 상세정보 수정 완료", null);
    }

    @DeleteMapping("/{provider_id}")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> deleteVoucherProvider(@PathVariable("provider_id") Long providerId) {
        voucherProviderService.delete(providerId);

        return new ApiResponse<>(HttpStatus.OK, "상품권 제조사 삭제 완료", null);
    }
}
