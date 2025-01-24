package com.pintoss.gitftmall.infra.persistence.voucher;

import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherProviderJpaRepository extends JpaRepository<VoucherProvider,Long> {
    boolean existsByName(String name);

    Optional<VoucherProvider> findById(Long id);

    Page<VoucherProvider> findByIsPopularTrue(Pageable pageable);

    Page<VoucherProvider> findByCategory(VoucherProviderCategory category, Pageable pageable);
}
