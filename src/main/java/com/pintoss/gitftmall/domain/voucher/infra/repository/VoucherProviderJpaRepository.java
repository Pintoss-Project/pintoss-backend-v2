package com.pintoss.gitftmall.domain.voucher.infra.repository;

import com.pintoss.gitftmall.domain.voucher.domain.VoucherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherProviderJpaRepository extends JpaRepository<VoucherProvider,Long> {
    boolean existsByName(String name);
}
