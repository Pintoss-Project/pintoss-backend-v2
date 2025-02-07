package com.pintoss.gitftmall.infra.persistence.voucherProvider;

import com.pintoss.gitftmall.domain.voucherProvider.model.VoucherProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherProviderJpaRepository extends JpaRepository<VoucherProvider,Long> {
    boolean existsByName(String name);
}
