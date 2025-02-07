package com.pintoss.gitftmall.infra.persistence.voucherProvider;

import com.pintoss.gitftmall.domain.voucherProvider.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VoucherProviderRepositoryImpl implements IVoucherProviderRepository {

    private final VoucherProviderJpaRepository voucherProviderJpaRepository;

    @Override
    public void save(VoucherProvider voucherProvider) {
        voucherProviderJpaRepository.save(voucherProvider);
    }

    @Override
    public boolean existsByName(String name) {
        return voucherProviderJpaRepository.existsByName(name);
    }
}
