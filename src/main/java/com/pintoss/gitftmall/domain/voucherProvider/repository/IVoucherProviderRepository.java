package com.pintoss.gitftmall.domain.voucherProvider.repository;

import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.model.VoucherProvider;
import java.util.List;

public interface IVoucherProviderRepository {
    void save(VoucherProvider voucherProvider);

    boolean existsByName(String name);

    List<VoucherProviderListResponse> findAll();
}
