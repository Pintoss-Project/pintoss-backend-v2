package com.pintoss.gitftmall.domain.voucher.mapper;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherProviderDetailResponse;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class VoucherProviderDetailMapper {

    public VoucherProviderDetailResponse toVoucherProviderDetailResponse(VoucherProvider voucherProvider) {
        return new VoucherProviderDetailResponse(
                voucherProvider.getId(),
                voucherProvider.getName(),
                voucherProvider.isPopular(),
                voucherProvider.getContactInfo().getHomePage().getUrl(),
                voucherProvider.getContactInfo().getCsCenter().getTel(),
                voucherProvider.getDiscount().getCardDiscount(),
                voucherProvider.getDiscount().getPhoneDiscount(),
                voucherProvider.getDescription(),
                voucherProvider.getCategory().toString().toLowerCase(),
                voucherProvider.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                voucherProvider.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                voucherProvider.getLogoImageUrl()
        );
    }
}
