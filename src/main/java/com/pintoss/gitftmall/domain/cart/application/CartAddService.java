package com.pintoss.gitftmall.domain.cart.application;

import com.pintoss.gitftmall.domain.cart.application.dto.CartAddServiceCommand;
import com.pintoss.gitftmall.domain.cart.domain.Cart;
import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import com.pintoss.gitftmall.domain.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartAddService {

    private final CartRepository cartRepository;

    public void addCartItem(CartAddServiceCommand command) {

        Cart cart = cartRepository.findByMemberId(command.getUserId())
                .orElseGet(() -> new Cart(command.getUserId()));

        List<CartItem> cartItems = command.getCartItems().stream().map(item ->
                CartItem.create(item.getVoucherId(), item.getQuantity())).toList();

        cart.addItems(cartItems);

        cartRepository.save(cart);
    }
}
