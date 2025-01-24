package com.pintoss.gitftmall.domain.voucher.application.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VoucherRegisterServiceCommand {

    private String name;

    private BigDecimal price;

    private int stock;

    public VoucherRegisterServiceCommand(
            String name, BigDecimal price, int stock
    ){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
