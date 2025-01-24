package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.voucher.application.VoucherProviderService;
import com.pintoss.gitftmall.domain.voucher.application.VoucherService;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderDetailResponse;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderResponse;
import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voucher-providers")
@AllArgsConstructor
public class VoucherProviderController {

    private final VoucherProviderService voucherProviderService;
    private final VoucherService voucherService;

    @GetMapping
    public ApiResponse<Page<VoucherProviderResponse>> getVoucherProviders(
            @RequestParam(required = false) String category,
            @PageableDefault Pageable pageable
    ) {
        Page<VoucherProviderResponse> voucherProviderResponsePage;
        String message = "";

        if(category == null) {
            // 전체조회
            voucherProviderResponsePage = voucherProviderService.getAll(pageable);
            message = "전체 상품권 제조사 조회 완료";
        }
        else {
            // 카테고리 조회
            voucherProviderResponsePage = voucherProviderService.getByCategory(category, pageable);
            message = "카테고리 상품권 제조사 조회 완료";
        }
        return ApiResponse.of(HttpStatus.OK, message, voucherProviderResponsePage);
    }

    @GetMapping("/popular")
    public ApiResponse<Page<VoucherProviderResponse>> getPopularVoucherProviders(Pageable pageable) {
        Page<VoucherProviderResponse> voucherProviderResponsePage = voucherProviderService.getPopular(pageable);

        return ApiResponse.of(HttpStatus.OK, "인기 상품권 제조사 조회 완료", voucherProviderResponsePage);
    }

    @GetMapping("/{provider_id}")
    public ApiResponse<VoucherProviderDetailResponse> getVoucherProviderDetail(@PathVariable("provider_id") Long providerId) {
        VoucherProviderDetailResponse voucherProviderDetail = voucherProviderService.getDetail(providerId);

        return ApiResponse.of(HttpStatus.OK, "상품권 제조사 상세정보 조회 완료", voucherProviderDetail);
    }

    @GetMapping("/{provider_id}/vouchers")
    public ApiResponse<Page<VoucherResponse>> getVouchers(
            @PathVariable("provider_id") Long providerId,
            @PageableDefault Pageable pageable
    ) {
        Page<VoucherResponse> voucherResponsePage = voucherService.getAll(providerId, pageable);

        return ApiResponse.of(HttpStatus.OK, "상품권 리스트 조회 완료", voucherResponsePage);
    }
}
