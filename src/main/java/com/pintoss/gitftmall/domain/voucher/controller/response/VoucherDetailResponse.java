package com.pintoss.gitftmall.domain.voucher.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VoucherDetailResponse {

    private Long voucher_id;

    private String name;

    private BigDecimal price;

    private int stock;

    private String created_at;
}
