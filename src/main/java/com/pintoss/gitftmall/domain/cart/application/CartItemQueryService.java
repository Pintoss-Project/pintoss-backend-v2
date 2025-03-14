package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemListResponse;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemQueryService {

    private final CartItemRepository cartItemRepository;

    public List<CartItemListResponse> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
}
