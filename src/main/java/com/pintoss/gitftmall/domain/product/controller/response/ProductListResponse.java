package com.pintoss.gitftmall.domain.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductListResponse {
    private Long product_id;

    private String name;

    private BigDecimal card_discount;

    private BigDecimal phone_discount;

    private String image_url;
}
