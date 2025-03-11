package com.pintoss.gitftmall.domain.cart.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemAddRequest {
    private Long voucherId;

    private int quantity;
}
