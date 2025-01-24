package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherException;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vouchers")
@RequiredArgsConstructor
public class AdminVoucherController {

    private final IVoucherRepository voucherRepository;

    @DeleteMapping("/{voucher_id}")
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<String> deleteVoucher(@PathVariable("voucher_id") Long voucherId) {
        try {
            voucherRepository.deleteById(voucherId);
            return ApiResponse.of(HttpStatus.OK, "상품권 삭제 완료");
        } catch (InvalidVoucherException e) {
            throw new InvalidVoucherException(ErrorCode.BAD_REQUEST, "해당 Voucher ID는 존재하지 않습니다.");
        }
    }
}
