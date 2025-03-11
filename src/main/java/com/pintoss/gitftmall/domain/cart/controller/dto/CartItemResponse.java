package com.pintoss.gitftmall.domain.cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long cartId;
    private Long productId;
    private int quantity;
    private Long voucherId;
    private String name;
    private Long price;
    private String imageUrl;
}
