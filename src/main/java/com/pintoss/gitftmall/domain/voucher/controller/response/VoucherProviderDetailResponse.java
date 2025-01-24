package com.pintoss.gitftmall.domain.voucher.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class VoucherProviderDetailResponse {

    private Long voucher_provider_id;

    private String name;

    private boolean isPopular;

    private String home_page;

    private String cs_center;

    private BigDecimal card_discount;

    private BigDecimal phone_discount;

    private String description;

    private String category;

    private String created_at;

    private String updated_at;

    private String image_url;
}
