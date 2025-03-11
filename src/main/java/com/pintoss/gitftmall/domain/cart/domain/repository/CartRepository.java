package com.pintoss.gitftmall.domain.cart.domain.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemResponse;
import com.pintoss.gitftmall.domain.cart.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByMemberId(Long userId);

    void save(Cart cart);

    public List<CartItemResponse> findCartItemsByMemberId(Long memberId);
}
