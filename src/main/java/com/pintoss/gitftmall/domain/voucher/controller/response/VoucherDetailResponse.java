package com.pintoss.gitftmall.domain.voucher.controller.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class VoucherDetailResponse {
    private Long voucherId;
    private String voucherName;
    private Integer stock;
    private Long price;

    @QueryProjection

    public VoucherDetailResponse(Long voucherId, String voucherName, Integer stock, Long price) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.stock = stock;
        this.price = price;
    }
}
