package com.pintoss.gitftmall.domain.cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemsAddRequest {
    List<CartItemAddRequest> cartItems;
}