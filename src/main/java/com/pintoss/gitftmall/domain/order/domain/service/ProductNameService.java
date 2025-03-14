package com.pintoss.gitftmall.domain.order.domain.service;

import com.pintoss.gitftmall.domain.voucher.domain.VoucherProvider;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductNameService {
    public String generateProductName(VoucherProvider voucherProvider, Set<Long> providerIds) {
        if (voucherProvider == null) {
            throw new IllegalArgumentException("존재하지 않는 상품 제공사입니다.");
        }
        if (providerIds.size() == 1) {
            return voucherProvider.getName();
        }
        return voucherProvider.getName() + " 외 " + (providerIds.size() - 1) + "건";
    }
}
