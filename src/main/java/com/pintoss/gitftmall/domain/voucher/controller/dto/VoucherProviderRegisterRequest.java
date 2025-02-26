package com.pintoss.gitftmall.domain.voucher.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class VoucherProviderRegisterRequest {

    @NotBlank(message = "상품 제공사 이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "상품 코드 번호는 필수 항목입니다.")
    private String code;

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

    @NotBlank(message = "로고 이미지는 필수 항목입니다.")
    private String imageUrl;

    @NotBlank(message = "상품 유의사항은 필수 항목입니다.")
    private String note;

    @NotEmpty(message = "상품권 종류는 1개 이상이어야합니다.")
    private List<VoucherRegisterRequest> vouchers = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class VoucherRegisterRequest {

        @NotBlank(message = "상품권 명은 필수 항목입니다.")
        private String name;

        @NotNull(message = "상품권 가격은 필수 항목입니다.")
        @Min(value = 0, message = "상품권 가격은 0 이상이어야 합니다.")
        private Long price;

        @NotNull(message = "재고 수량은 필수 항목입니다.")
        @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
        private Integer stock;
    }
}
