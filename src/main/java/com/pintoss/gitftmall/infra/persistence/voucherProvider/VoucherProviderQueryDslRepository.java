package com.pintoss.gitftmall.infra.persistence.voucherProvider;

import static com.pintoss.gitftmall.domain.voucherProvider.model.QVoucherProvider.voucherProvider;

import com.pintoss.gitftmall.domain.voucherProvider.controller.response.QVoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.controller.response.VoucherProviderListResponse;
import com.pintoss.gitftmall.domain.voucherProvider.model.QVoucherProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VoucherProviderQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<VoucherProviderListResponse> findAll(){
        return queryFactory.select(
            new QVoucherProviderListResponse(
                voucherProvider.id,
                voucherProvider.name,
                voucherProvider.isPopular,
                voucherProvider.discount,
                voucherProvider.contactInfo,
                voucherProvider.description,
                voucherProvider.publisher,
                voucherProvider.note
            )
        ).from(voucherProvider)
            .fetch();
    }
}
