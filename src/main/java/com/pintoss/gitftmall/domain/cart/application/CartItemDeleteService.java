package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.NotFoundException;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartItemDeleteService {

    private final CartItemRepository cartItemRepository;

    @Transactional
    public void deleteCartItem(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndId(userId, cartItemId)
                .orElseThrow( () -> new NotFoundException(ErrorCode.NOT_FOUND_CART_ITEM));
        cartItem.deleted();
        cartItemRepository.save(cartItem);
    }
}
