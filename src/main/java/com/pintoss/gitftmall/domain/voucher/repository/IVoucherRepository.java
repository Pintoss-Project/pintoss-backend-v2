package com.pintoss.gitftmall.domain.voucher.repository;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;

import java.util.Optional;

public interface IVoucherRepository {
    Optional<Voucher> findById(Long id);

    void save(Voucher voucher);
}
