package com.pintoss.gitftmall.domain.order.application.command;

import com.pintoss.gitftmall.domain.order.controller.request.OrderItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderCreateServiceCommand {
    private Long ordererId;
    private List<OrderItemRequest> orderItems;
}
