package com.pintoss.gitftmall.domain.order.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderCreateRequest {
    private Long providerId;
    private List<OrderItemRequest> orderItems;
}
