package com.pintoss.gitftmall.domain.voucher.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VoucherRegisterServiceCommand {

    private Long voucherProviderId;

    private BigDecimal cardDiscount = BigDecimal.ZERO;

    private BigDecimal phoneDiscount = BigDecimal.ZERO;

    private Long price;

    private Integer stock;

}
