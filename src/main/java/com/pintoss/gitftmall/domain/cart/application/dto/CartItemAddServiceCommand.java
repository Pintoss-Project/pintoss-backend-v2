package com.pintoss.gitftmall.domain.cart.application.dto;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemAddRequest;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
public class CartItemAddServiceCommand {

    private Long userId;
    private Map<Long, Integer> cartItems;

    private CartItemAddServiceCommand(Long userId, Map<Long, Integer> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public static CartItemAddServiceCommand from(Long userId, List<CartItemAddRequest> request) {
        Map<Long, Integer> cartItems = request.stream()
                .collect(Collectors.toMap(
                        CartItemAddRequest::getVoucherId,
                        CartItemAddRequest::getQuantity
                ));

        return new CartItemAddServiceCommand(userId, cartItems);
    }

    public List<Long> getCartItemIds() {
        return cartItems.keySet().stream().toList();
    }
}
