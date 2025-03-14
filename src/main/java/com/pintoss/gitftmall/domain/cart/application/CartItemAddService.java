package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.domain.cart.application.dto.CartItemAddServiceCommand;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemAddService {

    private final CartItemRepository cartItemRepository;

    public void addCartItem(CartItemAddServiceCommand command) {
        List<CartItem> existingCarItems = cartItemRepository.findByUserIdAndProductIdIn(command.getUserId(), command.getCartItemIds());

        Set<Long> existingProductIds = existingCarItems.stream()
                .map(CartItem::getProductId)
                .collect(Collectors.toSet());

        existingCarItems.forEach(cartItem -> {
            Integer quantity = command.getCartItems().get(cartItem.getProductId());
            cartItem.increaseQuantity(quantity);
        });

        List<CartItem> newCartItems = command.getCartItems().entrySet().stream()
                .filter(entry -> !existingProductIds.contains(entry.getKey()))
                        .map(entry -> CartItem.create(command.getUserId(), entry.getKey(), entry.getValue()))
                                .toList();

        List<CartItem> allCartItems = new ArrayList<>(existingCarItems);
        allCartItems.addAll(newCartItems);

        cartItemRepository.saveAll(allCartItems);
    }
}
