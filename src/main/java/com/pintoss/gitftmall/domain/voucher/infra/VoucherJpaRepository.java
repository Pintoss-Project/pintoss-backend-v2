package com.pintoss.gitftmall.domain.voucher.infra;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherJpaRepository extends JpaRepository<Voucher, Long> {
}
