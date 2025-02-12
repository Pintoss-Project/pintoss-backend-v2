package com.pintoss.gitftmall.domain.voucher.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoucherRegisterServiceCommand {

    private Long voucherProviderId;

    private String name;

    private Long price;

    private Integer stock;

}
