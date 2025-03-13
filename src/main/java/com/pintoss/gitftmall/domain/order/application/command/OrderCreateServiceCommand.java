package com.pintoss.gitftmall.domain.order.application.command;

import com.pintoss.gitftmall.domain.order.controller.request.OrderCreateRequest;
import com.pintoss.gitftmall.domain.order.controller.request.OrderItemRequest;
import com.pintoss.gitftmall.domain.order.domain.vo.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderCreateServiceCommand {

    private Long ordererId;
    private PaymentMethodType paymentMethodType;
    private List<OrderItemRequest> orderItems;

    public static OrderCreateServiceCommand from(Long ordererId, OrderCreateRequest request) {
        return new OrderCreateServiceCommand(
                ordererId,
                request.getPaymentMethod(),
                request.getOrderItems()
        );
    }
}
