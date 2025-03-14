package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemListResponse;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.pintoss.gitftmall.domain.cart.domain.QCartItem.cartItem;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucher.voucher;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucherProvider.voucherProvider;

@Repository
@RequiredArgsConstructor
public class CartItemQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public List<CartItemListResponse> findByUserId(Long userId) {
        return queryFactory
                .select(
                        Projections.constructor(CartItemListResponse.class,
                                cartItem.id.as("cartId"),
                                voucher.id.as("productId"),
                                cartItem.quantity,
                                voucher.name,
                                voucher.price,
                                voucherProvider.imageUrl
                        )
                )
                .from(cartItem)
                .join(voucher).on(cartItem.productId.eq(voucher.id))
                .join(voucherProvider).on(voucher.voucherProviderId.eq(voucherProvider.id))
                .where(
                        cartItem.userId.eq(userId),
                        cartItem.isDeleted.isFalse()
                )
                .fetch();
    }

    public List<CartItem> findByUserIdAndProductIdIn(Long userId, List<Long> productIds) {
        return queryFactory
                .selectFrom(cartItem)
                .where(
                        cartItem.userId.eq(userId),
                        cartItem.productId.in(productIds),
                        cartItem.isDeleted.isFalse()
                )
                .fetch();
    }

    public Optional<CartItem> findByUserIdAndId(Long userId, Long cartItemId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(cartItem)
                        .where(
                                cartItem.userId.eq(userId),
                                cartItem.id.eq(cartItemId),
                                cartItem.isDeleted.isFalse()
                        ).fetchOne()
        );
    }
}
