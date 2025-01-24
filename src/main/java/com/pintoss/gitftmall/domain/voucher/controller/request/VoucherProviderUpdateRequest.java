package com.pintoss.gitftmall.domain.voucher.controller.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
public class VoucherProviderUpdateRequest {

    private Optional<String> name = Optional.empty();

    @JsonProperty("isPopular")
    private Optional<Boolean> isPopular = Optional.empty();

    @JsonProperty("home_page")
    private Optional<String> homePage = Optional.empty();

    @JsonProperty("cs_center")
    private Optional<String> csCenter = Optional.empty();

    @Min(value = 0, message = "카드 할인 금액은 0 이상이어야 합니다.")
    @JsonProperty("card_discount")
    private BigDecimal cardDiscount = BigDecimal.ZERO;

    @Min(value = 0, message = "전화 할인 금액은 0 이상이어야 합니다.")
    @JsonProperty("phone_discount")
    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    private Optional<String> description = Optional.empty();

    private Optional<String> publisher = Optional.empty();

    @JsonProperty("image_url")
    private Optional<String> logoImageUrl = Optional.empty();

    private Optional<String> note = Optional.empty();

}
