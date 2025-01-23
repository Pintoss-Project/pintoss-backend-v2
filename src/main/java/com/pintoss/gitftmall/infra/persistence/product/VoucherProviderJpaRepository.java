package com.pintoss.gitftmall.infra.persistence.product;

import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherProviderJpaRepository extends JpaRepository<VoucherProvider,Long> {
    boolean existsByName(String name);

    Page<VoucherProvider> findByIsPopularTrue(Pageable pageable);

    Page<VoucherProvider> findByCategory(VoucherProviderCategory category, Pageable pageable);
}
