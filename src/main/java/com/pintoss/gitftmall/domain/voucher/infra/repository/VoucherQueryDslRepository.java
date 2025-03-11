package com.pintoss.gitftmall.domain.voucher.infra.repository;

import com.pintoss.gitftmall.domain.voucher.controller.dto.QVoucherDetailResponse;
import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherDetailResponse;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pintoss.gitftmall.domain.voucher.domain.QVoucher.voucher;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucherProvider.voucherProvider;

@Repository
@RequiredArgsConstructor
public class VoucherQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<VoucherDetailResponse> findByVoucherProviderId(Long providerId) {
        return queryFactory.select(
                        new QVoucherDetailResponse(
                                voucher.id,
                                voucher.name,
                                voucher.stock,
                                voucher.price
                                )
        )
                .from(voucher)
                .join(voucherProvider).on(voucher.voucherProviderId.eq(voucherProvider.id))
                .where(voucher.voucherProviderId.eq(providerId))
                .orderBy(voucher.price.desc())
                .fetch();
    }

    public List<Voucher> findAllByIds(List<Long> voucherIds) {
        return queryFactory.selectFrom(voucher)
                .where(voucher.id.in(voucherIds))
                .fetch();
    }
}
