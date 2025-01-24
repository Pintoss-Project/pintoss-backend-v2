package com.pintoss.gitftmall.domain.voucher.mapper;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherResponse;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
public class VoucherMapper {

    public Page<VoucherResponse> toVoucherResponsePage(Page<Voucher> voucherPage) {
        return new PageImpl<>(
                voucherPage.stream()
                        .map(this::toVoucherResponse)
                        .collect(Collectors.toList()),
                voucherPage.getPageable(),
                voucherPage.getTotalElements()
        );
    }

    private VoucherResponse toVoucherResponse(Voucher voucher) {
        return new VoucherResponse(
                voucher.getId(),
                voucher.getName(),
                voucher.getPrice(),
                voucher.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
