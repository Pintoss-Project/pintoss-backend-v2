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
public class VoucherProviderRegisterRequest {

    @NotBlank(message = "상품권 제조사 이름은 필수 항목입니다.")
    private String name;

    @NotNull(message = "인기 여부는 필수 항목입니다.")
    @JsonProperty("isPopular")
    private boolean isPopular = false;

    @NotBlank(message = "홈페이지 주소는 필수 항목입니다.")
    @JsonProperty("home_page")
    private String homePage;

    @NotBlank(message = "고객 센터 정보는 필수 항목입니다.")
    @JsonProperty("cs_center")
    private String csCenter;

    @NotNull(message = "카드 할인 금액은 필수 항목입니다.")
    @Min(value = 0, message = "카드 할인 금액은 0 이상이어야 합니다.")
    @JsonProperty("card_discount")
    private BigDecimal cardDiscount = BigDecimal.ZERO;

    @NotNull(message = "전화 할인 금액은 필수 항목입니다.")
    @Min(value = 0, message = "전화 할인 금액은 0 이상이어야 합니다.")
    @JsonProperty("phone_discount")
    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    @NotBlank(message = "설명은 필수 항목입니다.")
    private String description;

    @NotBlank(message = "발행자는 필수 항목입니다.")
    private String publisher;

    @NotNull(message = "카테고리는 필수 항목입니다.")
    private String category;

    @NotBlank(message = "로고 이미지는 필수 항목입니다.")
    @JsonProperty("image_url")
    private String logoImageUrl;

    @NotBlank(message = "상품 유의사항은 필수 항목입니다.")
    private String note;

    //상품권의 인덱스
    private int index;

    public VoucherProviderCategory getValidatedCategory() {
        return VoucherProviderCategory.from(this.category);
    }
}
