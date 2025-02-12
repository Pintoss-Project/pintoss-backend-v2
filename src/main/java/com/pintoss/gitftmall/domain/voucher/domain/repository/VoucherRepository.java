package com.pintoss.gitftmall.domain.voucher.domain.repository;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherDetailResponse;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository {
    Optional<Voucher> findById(Long id);

    void save(Voucher voucher);

    List<VoucherDetailResponse> findByVoucherProviderId(Long providerId);
}
