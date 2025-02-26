package com.pintoss.gitftmall.domain.voucher.application.dto;

import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherProviderRegisterRequest;
import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherRegisterRequest;
import java.util.List;
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

    private List<VoucherRegisterRequest> vouchers;

    public static VoucherProviderRegisterServiceCommand from (VoucherProviderRegisterRequest request) {
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
            request.getNote(),
            request.getVouchers()
        );
    }
}
