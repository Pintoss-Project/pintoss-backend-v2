package com.pintoss.gitftmall.domain.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "cart")
    private List<CartItem> items = new ArrayList<>();

    public Cart(Long memberId) {
         this.memberId = memberId;
    }

    public static Cart create(Long memberId) {
        return new Cart(memberId);
    }

    public void addItem(CartItem cartItem) {
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().increaseQuantity(cartItem.getQuantity());
        } else {
            cartItem.assignCart(this);
            items.add(cartItem);
        }
    }
    public void addItems(List<CartItem> cartItems) {
        cartItems.forEach(this::addItem);
    }

    public void removeItem(CartItem cartItem) {
        items.removeIf(item -> item.getProductId().equals(cartItem.getProductId()));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
