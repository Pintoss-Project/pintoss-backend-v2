package com.pintoss.gitftmall.domain.cart.application.dto;

import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemAddRequest;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemsAddRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CartAddServiceCommand {

    private Long userId;
    private List<CartItemAddRequest> cartItems ;

    public static CartAddServiceCommand from(Long userId, CartItemsAddRequest request) {
        return new CartAddServiceCommand(userId, request.getCartItems());
    }
}
