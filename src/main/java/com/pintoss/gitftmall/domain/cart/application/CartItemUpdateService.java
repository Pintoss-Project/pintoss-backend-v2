package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.NotFoundException;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemUpdateRequest;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemUpdateService {

    private final CartItemRepository cartItemRepository;

    public int update(Long userId, Long cartItemId, CartItemUpdateRequest request) {
        CartItem cartItem = cartItemRepository.findByUserIdAndId(userId, cartItemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CART_ITEM));

        cartItem.calculateQuantity(request.getQuantity());

        cartItemRepository.save(cartItem);
        return cartItem.getQuantity();
    }
}
