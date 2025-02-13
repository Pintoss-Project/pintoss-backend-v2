package com.pintoss.gitftmall.domain.order.infra.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println(event.getOrderId()+"번 order 생성");
    }
}
