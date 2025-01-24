package com.pintoss.gitftmall.domain.voucher.repository;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IVoucherRepository {

    void save(Voucher voucher);

    void delete(Voucher voucher);

    Optional<Voucher> findById(Long voucherId);

    Page<Voucher> findByVoucherProvider(VoucherProvider voucherProvider, Pageable pageable);
}
