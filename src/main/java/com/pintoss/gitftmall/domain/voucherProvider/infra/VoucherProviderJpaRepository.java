package com.pintoss.gitftmall.domain.voucherProvider.infra;

import com.pintoss.gitftmall.domain.voucherProvider.model.VoucherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherProviderJpaRepository extends JpaRepository<VoucherProvider,Long> {
    boolean existsByName(String name);
}
