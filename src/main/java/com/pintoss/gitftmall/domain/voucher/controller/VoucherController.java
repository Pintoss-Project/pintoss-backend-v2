package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherQueryService;
import com.pintoss.gitftmall.domain.voucher.application.VoucherRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.VoucherRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherRegisterRequest;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherRegisterService voucherRegisterService;
    private final VoucherQueryService voucherQueryService;

    @PostMapping()
    @AuthorizationRequired({RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<Void> registerVoucher(@RequestBody @Valid VoucherRegisterRequest request) {
        VoucherRegisterServiceCommand command = new VoucherRegisterServiceCommand(
                request.getVoucherProviderId(),
                request.getName(),
                request.getPrice(),
                request.getStock()
        );

        voucherRegisterService.register(command);

        return ApiResponse.ok(null);
    }

    @GetMapping
    public ApiResponse<List<VoucherDetailResponse>> findVoucherByProviderId(@RequestParam("providerId") String providerId) {

        List<VoucherDetailResponse> voucherList = voucherQueryService.findByVoucherProviderId(Long.parseLong(providerId));
        return ApiResponse.ok(voucherList);
    }

}
