package com.pintoss.gitftmall.infra.persistence.voucher;

import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherProviderRepositoryImpl implements IVoucherProviderRepository {

    private final VoucherProviderJpaRepository voucherProviderJpaRepository;

    @Override
    public void save(VoucherProvider voucherProvider) {
        voucherProviderJpaRepository.save(voucherProvider);
    }

    @Override
    public void delete(VoucherProvider voucherProvider) {
        voucherProviderJpaRepository.delete(voucherProvider);
    }

    @Override
    public boolean existsByName(String name) {
        return voucherProviderJpaRepository.existsByName(name);
    }

    @Override
    public Optional<VoucherProvider> findById(Long id) {
        return voucherProviderJpaRepository.findById(id);
    }

    @Override
    public Page<VoucherProvider> findAll(Pageable pageable) {
        return voucherProviderJpaRepository.findAll(pageable);
    }

    @Override
    public Page<VoucherProvider> findByIsPopularTrue(Pageable pageable) {
        return voucherProviderJpaRepository.findByIsPopularTrue(pageable);
    }

    @Override
    public Page<VoucherProvider> findByCategory(VoucherProviderCategory category, Pageable pageable) {
        return voucherProviderJpaRepository.findByCategory(category, pageable);
    }
}
