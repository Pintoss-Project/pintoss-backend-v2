package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemResponse;
import com.pintoss.gitftmall.domain.cart.domain.Cart;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository jpaRepository;
    private final CartQueryDslRepository queryDslRepository;

    @Override
    public Optional<Cart> findByMemberId(Long userId) {
        return jpaRepository.findByMemberId(userId);
    }

    @Override
    public void save(Cart cart) {
        jpaRepository.save(cart);
    }

    @Override
    public List<CartItemResponse> findCartItemsByMemberId(Long memberId) {
        return queryDslRepository.findCartItemsByMemberId(memberId);
    }
}
//select sub1.*, sub2.price, sub2.url from (
//        select
//                c.id as cart_id,
//        c.member_id as member_id,
//        ci.product_id as product_id,
//        ci.quantity
//                from cart c inner join cart_item ci on c.id = ci.cart_id where c.member_id = 1
//) as sub1
//inner join (select v.id as voucher_id, v.name , v.price, vp.url from voucher v inner join voucher_provider vp on v.voucher_provider_id = vp.id) sub2
//on sub1.product_id = sub2.voucher_id;