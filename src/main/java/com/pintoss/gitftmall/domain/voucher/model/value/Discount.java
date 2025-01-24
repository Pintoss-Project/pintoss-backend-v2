package com.pintoss.gitftmall.domain.voucher.model.value;

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

    public Discount update(BigDecimal cardDiscount, BigDecimal phoneDiscount) {
        return new Discount(
            cardDiscount != null ? cardDiscount : this.cardDiscount,
            phoneDiscount != null ? phoneDiscount : this.phoneDiscount
        );
    }
}
