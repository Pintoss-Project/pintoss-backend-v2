package com.pintoss.gitftmall.domain.voucherProvider.infra.repository;

import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.domain.repository.IVoucherProviderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VoucherProviderRepositoryImpl implements IVoucherProviderRepository {

    private final VoucherProviderJpaRepository jpaRepository;
    private final VoucherProviderQueryDslRepository queryDslRepository;

    @Override
    public void save(VoucherProvider voucherProvider) {
        jpaRepository.save(voucherProvider);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public List<VoucherProviderListResponse> findAll() {
        return queryDslRepository.findAll();
    }
}
