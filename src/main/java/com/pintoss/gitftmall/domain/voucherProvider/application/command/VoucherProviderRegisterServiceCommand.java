package com.pintoss.gitftmall.domain.voucherProvider.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class VoucherProviderRegisterServiceCommand {

    private String name;

    private boolean isPopular;

    private BigDecimal cardDiscount = BigDecimal.ZERO;

    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    private String homePage;

    private String csCenter;

    private String description;

    private String publisher;

    private String logoImageUrl;

    private String note;

    private int index;

    public VoucherProviderRegisterServiceCommand(String name, boolean isPopular,
        BigDecimal cardDiscount, BigDecimal phoneDiscount, String homePage, String csCenter,
        String description, String publisher, String logoImageUrl, String note, int index) {
        this.name = name;
        this.isPopular = isPopular;
        this.cardDiscount = cardDiscount;
        this.phoneDiscount = phoneDiscount;
        this.homePage = homePage;
        this.csCenter = csCenter;
        this.description = description;
        this.publisher = publisher;
        this.logoImageUrl = logoImageUrl;
        this.note = note;
        this.index = index;
    }
}
