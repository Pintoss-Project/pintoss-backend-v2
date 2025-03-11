package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pintoss.gitftmall.domain.cart.domain.QCart.cart;
import static com.pintoss.gitftmall.domain.cart.domain.QCartItem.cartItem;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucher.voucher;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucherProvider.voucherProvider;

@Repository
@RequiredArgsConstructor
public class CartQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<CartItemResponse> findCartItemsByMemberId(Long memberId) {
        return queryFactory
                .select(Projections.constructor(CartItemResponse.class,
                        cart.id.as("cartId"),
                        cartItem.productId,
                        cartItem.quantity,
                        voucher.id.as("voucherId"),
                        voucher.name,
                        voucher.price,
                        voucherProvider.imageUrl
                        ))
                .from(cart)
                .join(cart.items, cartItem)
                .join(voucher).on(cartItem.productId.eq(voucher.id))
                .join(voucherProvider).on(voucher.voucherProviderId.eq(voucherProvider.id))
                .where(cart.memberId.eq(memberId))
                .fetch();
    }
}
