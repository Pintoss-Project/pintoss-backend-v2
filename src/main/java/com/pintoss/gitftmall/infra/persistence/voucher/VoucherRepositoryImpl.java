package com.pintoss.gitftmall.infra.persistence.voucher;

import com.pintoss.gitftmall.common.exceptions.ErrorCode;
import com.pintoss.gitftmall.common.exceptions.voucher.InvalidVoucherException;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.model.VoucherProvider;
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
    public void deleteById(Long voucherId) {
        if (!voucherJpaRepository.existsById(voucherId)) {
            throw new InvalidVoucherException(ErrorCode.BAD_REQUEST, "해당 Voucher ID는 존재하지 않습니다.");
        }
        voucherJpaRepository.deleteById(voucherId);
    }

    @Override
    public Page<Voucher> findByVoucherProvider(VoucherProvider voucherProvider, Pageable pageable) {
        return voucherJpaRepository.findByVoucherProvider(voucherProvider, pageable);
    }
}
