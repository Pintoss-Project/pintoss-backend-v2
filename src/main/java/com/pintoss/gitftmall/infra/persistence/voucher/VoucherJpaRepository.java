package com.pintoss.gitftmall.infra.persistence.voucher;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherJpaRepository extends JpaRepository<Voucher, Long> {
    Page<Voucher> findByVoucherProvider(VoucherProvider voucherProvider, Pageable pageable);
}
