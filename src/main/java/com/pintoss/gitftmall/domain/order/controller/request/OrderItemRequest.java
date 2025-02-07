package com.pintoss.gitftmall.domain.order.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long voucherId;
    private Integer quantity;
}
