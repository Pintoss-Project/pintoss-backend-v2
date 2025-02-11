package com.pintoss.gitftmall.domain.voucher.infra;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VoucherRepositoryImpl implements IVoucherRepository {

    private final VoucherJpaRepository voucherJpaRepository;

    @Override
    public void save(Voucher voucher) {
        voucherJpaRepository.save(voucher);
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherJpaRepository.findById(id);
    }
}
