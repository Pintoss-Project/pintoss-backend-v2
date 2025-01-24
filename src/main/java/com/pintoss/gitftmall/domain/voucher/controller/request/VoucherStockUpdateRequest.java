package com.pintoss.gitftmall.domain.voucher.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VoucherStockUpdateRequest {

    @NotNull(message = "상품권 재고는 필수 항목입니다.")
    @Min(value = 0, message = "상품권 재고는 0 이상이어야 합니다.")
    @JsonProperty("stock")
    private int stock = 0;

}
