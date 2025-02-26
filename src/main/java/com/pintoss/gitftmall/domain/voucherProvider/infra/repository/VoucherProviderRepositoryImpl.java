package com.pintoss.gitftmall.domain.voucherProvider.infra.repository;

import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.domain.repository.VoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherProviderRepositoryImpl implements VoucherProviderRepository {

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

    @Override
    public Optional<VoucherProvider> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
