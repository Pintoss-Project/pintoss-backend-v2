package com.pintoss.gitftmall.domain.voucher.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VoucherProviderResponse {
    private Long voucher_provider_id;

    private String name;

    private String image_url;
}
