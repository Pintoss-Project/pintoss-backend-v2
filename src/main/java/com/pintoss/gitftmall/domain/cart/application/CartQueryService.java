package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemResponse;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartQueryService {

    private final CartRepository cartRepository;

    public List<CartItemResponse> getCartItems(Long userId) {
        return cartRepository.findCartItemsByMemberId(userId);
    }
}
