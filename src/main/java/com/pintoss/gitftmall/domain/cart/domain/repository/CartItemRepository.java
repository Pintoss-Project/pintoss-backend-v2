package com.pintoss.gitftmall.domain.cart.domain.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemListResponse;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository {
    void save(CartItem cartItem);

    void saveAll(List<CartItem> cartItems);

    List<CartItemListResponse> findByUserId(Long userId);

    List<CartItem> findByUserIdAndProductIdIn(Long userId, List<Long> productIds);

    int updateQuantity(Long userId, Long cartItemId, int quantity);

    Optional<CartItem> findByUserIdAndId(Long userId, Long cartItemId);
}
