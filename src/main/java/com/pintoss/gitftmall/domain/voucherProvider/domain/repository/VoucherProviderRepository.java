package com.pintoss.gitftmall.domain.voucherProvider.domain.repository;

import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.domain.VoucherProvider;

import java.util.List;
import java.util.Optional;

public interface VoucherProviderRepository {
    void save(VoucherProvider voucherProvider);

    boolean existsByName(String name);

    List<VoucherProviderListResponse> findAll();

    Optional<VoucherProvider> findById(Long id);
}
