package com.pintoss.gitftmall.domain.voucher.repository;

import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoucherProviderRepository {

    void save(VoucherProvider voucherProvider);

    boolean existsByName(String name);

    Page<VoucherProvider> findAll(Pageable pageable);

    Page<VoucherProvider> findByIsPopularTrue(Pageable pageable);

    Page<VoucherProvider> findByCategory(VoucherProviderCategory category, Pageable pageable);
}
