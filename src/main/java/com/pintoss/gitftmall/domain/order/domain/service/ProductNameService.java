package com.pintoss.gitftmall.domain.order.domain.service;

import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.VoucherProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductNameService {
    public String generateProductName(VoucherProvider voucherProvider, List<Voucher> vouchers) {
        if (voucherProvider == null) {
            throw new IllegalArgumentException("존재하지 않는 상품 제공사입니다.");
        }
        int size = vouchers.stream().map(voucher -> voucher.getVoucherProviderId()).collect(Collectors.toSet()).size();
        if (size == 1) {
            return voucherProvider.getName();
        }
        return voucherProvider.getName() + " 외 " + (size - 1) + "건";
    }
}
