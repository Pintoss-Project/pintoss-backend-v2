package com.pintoss.gitftmall.domain.voucher.mapper;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderResponse;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VoucherProviderMapper {

    public Page<VoucherProviderResponse> toProductListResponseList(Page<VoucherProvider> voucherProviderPage) {
        return new PageImpl<>(
                voucherProviderPage.stream()
                        .map(this::toVoucherProviderResponse)
                        .collect(Collectors.toList()),
                voucherProviderPage.getPageable(),
                voucherProviderPage.getTotalElements()
        );
    }

    private VoucherProviderResponse toVoucherProviderResponse(VoucherProvider voucherProvider) {
        return new VoucherProviderResponse(
                voucherProvider.getId(),
                voucherProvider.getName(),
                voucherProvider.getImageUrl()
        );
    }
}
