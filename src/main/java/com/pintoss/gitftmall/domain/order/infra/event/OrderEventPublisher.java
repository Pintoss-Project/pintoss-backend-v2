package com.pintoss.gitftmall.domain.order.infra.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(OrderCreatedEvent event) {
        publisher.publishEvent(event);
    }
}
