package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherException;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherProviderIdException;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.VoucherService;
import com.pintoss.gitftmall.domain.voucher.controller.request.VoucherStockUpdateRequest;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vouchers")
@RequiredArgsConstructor
public class AdminVoucherController {

    private final VoucherService voucherService;
    private final IVoucherRepository voucherRepository;

    @PatchMapping("/{voucher_id}")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> updateVoucher(
            @PathVariable("voucher_id") Long voucherId,
            @RequestBody @Valid VoucherStockUpdateRequest request
    ) {
        try {
            voucherService.update(voucherId, request);
            return ApiResponse.of(HttpStatus.OK, "상품권 수량 수정 완료", null);
        } catch (Exception e) {
            throw new InvalidVoucherProviderIdException(ErrorCode.BAD_REQUEST, "해당 VoucherProvider ID는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/{voucher_id}")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> deleteVoucher(@PathVariable("voucher_id") Long voucherId) {
        try {
            voucherRepository.deleteById(voucherId);
            return ApiResponse.of(HttpStatus.OK, "상품권 삭제 완료", null);
        } catch (InvalidVoucherException e) {
            throw new InvalidVoucherException(ErrorCode.BAD_REQUEST, "해당 Voucher ID는 존재하지 않습니다.");
        }
    }
}
