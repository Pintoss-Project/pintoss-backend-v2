package com.pintoss.gitftmall.domain.voucherProvider.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoucherProviderRegisterRequest {

    @NotBlank(message = "상품 이름은 필수 항목입니다.")
    private String name;

    @NotNull(message = "인기 여부는 필수 항목입니다.")
    private boolean isPopular = false;

    @NotNull(message = "카드 할인 금액은 필수 항목입니다.")
    @Min(value = 0, message = "카드 할인 금액은 0 이상이어야 합니다.")
    private BigDecimal cardDiscount = BigDecimal.ZERO;

    @NotNull(message = "전화 할인 금액은 필수 항목입니다.")
    @Min(value = 0, message = "전화 할인 금액은 0 이상이어야 합니다.")
    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    @NotBlank(message = "홈페이지 주소는 필수 항목입니다.")
    private String homePage;

    @NotBlank(message = "고객 센터 정보는 필수 항목입니다.")
    private String csCenter;

    @NotBlank(message = "설명은 필수 항목입니다.")
    private String description;

    @NotBlank(message = "발행자는 필수 항목입니다.")
    private String publisher;

//    @NotNull(message = "카테고리는 필수 항목입니다.")
//    @Enumerated(EnumType.STRING)
//    private ProductCategory category;

    @NotBlank(message = "로고 이미지는 필수 항목입니다.")
    private String logoImageUrl;

    @NotBlank(message = "상품 유의사항은 필수 항목입니다.")
    private String note;

    //상품권의 인덱스
    private int index;
}
