package com.pintoss.gitftmall.domain.product.controller.response;

import com.pintoss.gitftmall.domain.product.model.value.Image;
import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSearchResponse {

    private Long id;
    private String name;
    private BigDecimal cardDiscount;
    private BigDecimal phoneDiscount;
    private List<String> images;

    @QueryProjection
    public ProductSearchResponse(Long id, String name, BigDecimal cardDiscount, BigDecimal phoneDiscount, List<String> images) {
        this.id = id;
        this.name = name;
        this.cardDiscount = cardDiscount;
        this.phoneDiscount = phoneDiscount;
        this.images = images;
    }
}
