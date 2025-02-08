package com.pintoss.gitftmall.domain.voucher.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VoucherRegisterRequest {

    @NotNull(message = "상품권 제공사는 필수 항목입니다.")
    private Long voucherProviderId;

//    @NotNull(message = "카드 할인 금액은 필수 항목입니다.")
//    @Min(value = 0, message = "카드 할인 금액은 0 이상이어야 합니다.")
//    private BigDecimal cardDiscount = BigDecimal.ZERO;
//
//    @NotNull(message = "전화 할인 금액은 필수 항목입니다.")
//    @Min(value = 0, message = "전화 할인 금액은 0 이상이어야 합니다.")
//    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    @NotNull(message = "상품권 가격은 필수 항목입니다.")
    @Min(value = 0, message = "상품권 가격은 0 이상이어야 합니다.")
    private Long price;

    @NotNull(message = "재고 수량은 필수 항목입니다.")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
    private Integer stock;

}
