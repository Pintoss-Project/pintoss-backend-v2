package com.pintoss.gitftmall.domain.voucher.domain.repository;

import com.pintoss.gitftmall.domain.voucher.domain.Voucher;

import java.util.Optional;

public interface VoucherRepository {
    Optional<Voucher> findById(Long id);

    void save(Voucher voucher);
}
