package com.pintoss.gitftmall.domain.voucher.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VoucherRegisterRequest {

    @NotBlank(message = "상품 이름은 필수 항목입니다.")
    private String name;

    @NotNull(message = "상품권 금액은 필수 항목입니다.")
    @Min(value = 0, message = "상품권 금액은 0 이상이어야 합니다.")
    @JsonProperty("price")
    private BigDecimal price = BigDecimal.ZERO;

    @NotNull(message = "상품권 재고는 필수 항목입니다.")
    @Min(value = 0, message = "상품권 재고는 0 이상이어야 합니다.")
    @JsonProperty("stock")
    private int stock = 0;
}
