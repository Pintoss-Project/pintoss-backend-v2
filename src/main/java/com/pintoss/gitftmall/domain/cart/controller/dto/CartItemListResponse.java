package com.pintoss.gitftmall.domain.cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemListResponse {
    private Long cartId;
    private Long productId;
    private int quantity;
    private String name;
    private Long price;
    private String imageUrl;
}
