package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemListResponse;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartItemQueryDslRepository cartItemQueryDslRepository;

    @Override
    public void save(CartItem cartItem) {
        cartItemJpaRepository.save(cartItem);
    }

    @Override
    public void saveAll(List<CartItem> cartItems) {
        cartItemJpaRepository.saveAll(cartItems);
    }

    @Override
    public List<CartItemListResponse> findByUserId(Long userId) {
        return cartItemQueryDslRepository.findByUserId(userId);
    }

    @Override
    public List<CartItem> findByUserIdAndProductIdIn(Long userId, List<Long> productIds) {
        return cartItemQueryDslRepository.findByUserIdAndProductIdIn(userId, productIds);
    }

    @Override
    public int updateQuantity(Long userId, Long cartItemId, int quantity) {
        return cartItemJpaRepository.updateQuantity(userId, cartItemId, quantity);
    }

    @Override
    public Optional<CartItem> findByUserIdAndId(Long userId, Long cartItemId) {
        return cartItemQueryDslRepository.findByUserIdAndId(userId, cartItemId);
    }
}
