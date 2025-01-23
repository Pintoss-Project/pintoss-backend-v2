package com.pintoss.gitftmall.domain.voucher.repository;

import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductRepository {

    void save(VoucherProvider product);

    boolean existsByName(String name);

    Page<VoucherProvider> findAll(Pageable pageable);

    Page<VoucherProvider> findByIsPopularTrue(Pageable pageable);

    Page<VoucherProvider> findByCategory(ProductCategory category, Pageable pageable);
}
