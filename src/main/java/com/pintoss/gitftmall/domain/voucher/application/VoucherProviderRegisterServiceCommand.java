package com.pintoss.gitftmall.domain.voucher.application;

import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherProviderRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VoucherProviderRegisterServiceCommand {

    private String name;

    private String code;

    private BigDecimal cardDiscount = BigDecimal.ZERO;

    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    private String homePage;

    private String csCenter;

    private String description;

    private String publisher;

    private String imageUrl;

    private String note;

    public static VoucherProviderRegisterServiceCommand from(VoucherProviderRegisterRequest request) {
        return new VoucherProviderRegisterServiceCommand(
                request.getName(),
                request.getCode(),
                request.getCardDiscount(),
                request.getPhoneDiscount(),
                request.getHomePage(),
                request.getCsCenter(),
                request.getDescription(),
                request.getPublisher(),
                request.getImageUrl(),
                request.getNote()
        );
    }
}
