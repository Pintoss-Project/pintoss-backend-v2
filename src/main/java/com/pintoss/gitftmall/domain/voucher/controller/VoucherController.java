package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.voucher.application.VoucherService;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/voucher-providers/{provider_id}")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping("/vouchers")
    public ApiResponse<?> getVouchers(
            @PathVariable("provider_id") Long providerId,
            @PageableDefault Pageable pageable
    ) {
        Page<VoucherResponse> voucherResponsePage = voucherService.getAll(providerId, pageable);

        return ApiResponse.of(HttpStatus.OK, "상품권 리스트 조회 완료", voucherResponsePage);
    }
}
