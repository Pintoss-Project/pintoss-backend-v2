package com.pintoss.gitftmall.domain.voucherProvider.model.value;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Discount {

    private BigDecimal cardDiscount;
    private BigDecimal phoneDiscount;

    public Discount(BigDecimal cardDiscount, BigDecimal phoneDiscount) {
        this.cardDiscount = cardDiscount;
        this.phoneDiscount = phoneDiscount;
    }

}
