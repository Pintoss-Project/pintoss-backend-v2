package com.pintoss.gitftmall.domain.voucher.infra.repository;

import com.pintoss.gitftmall.domain.voucher.controller.dto.QVoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucher.controller.dto.VoucherProviderListResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pintoss.gitftmall.domain.voucher.domain.QVoucherProvider.voucherProvider;

@Repository
@RequiredArgsConstructor
public class VoucherProviderQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<VoucherProviderListResponse> findAll(){
        return queryFactory.select(
            new QVoucherProviderListResponse(
                voucherProvider.id,
                voucherProvider.name,
                voucherProvider.discount,
                voucherProvider.contactInfo,
                voucherProvider.description,
                voucherProvider.publisher,
                voucherProvider.imageUrl,
                voucherProvider.note
            )
        ).from(voucherProvider)
            .fetch();
    }
}
