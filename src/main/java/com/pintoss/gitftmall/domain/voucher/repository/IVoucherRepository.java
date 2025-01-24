package com.pintoss.gitftmall.domain.voucher.repository;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVoucherRepository {

    void save(Voucher voucher);

    Page<Voucher> findAll(Pageable pageable);
}
