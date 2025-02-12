package com.pintoss.gitftmall.domain.voucher.infra.repository;

import com.pintoss.gitftmall.domain.voucher.controller.response.VoucherDetailResponse;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherRepositoryImpl implements VoucherRepository {

    private final VoucherJpaRepository voucherJpaRepository;
    private final VoucherQueryDslRepository voucherQueryDslRepository;

    @Override
    public void save(Voucher voucher) {
        voucherJpaRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherJpaRepository.findById(id);
    }

    @Override
    public List<VoucherDetailResponse> findByVoucherProviderId(Long providerId) {
        return voucherQueryDslRepository.findByVoucherProviderId(providerId);
    }
}
