package com.pintoss.gitftmall.infra.persistence.voucher;

import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class VoucherRepositoryImpl implements IVoucherRepository {

    private final VoucherJpaRepository voucherJpaRepository;

    @Override
    public void save(Voucher voucher) {
        voucherJpaRepository.save(voucher);
    }

    @Override
    public Page<Voucher> findAll(Pageable pageable) {
        return voucherJpaRepository.findAll(pageable);
    }
}
