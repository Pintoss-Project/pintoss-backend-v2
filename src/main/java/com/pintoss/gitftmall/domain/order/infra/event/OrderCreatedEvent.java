package com.pintoss.gitftmall.domain.order.infra.event;

import lombok.Getter;

@Getter
public class OrderCreatedEvent {

    private Long orderId;

    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;
    }
}
