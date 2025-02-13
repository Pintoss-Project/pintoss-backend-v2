package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.domain.Order;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.order.infra.event.OrderCreatedEvent;
import com.pintoss.gitftmall.domain.order.infra.event.OrderEventPublisher;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;
    private final OrderEventPublisher orderEventPublisher;

    public Long create(OrderCreateServiceCommand command) {
        List<OrderItem> orderItems = command.getOrderItems().stream().map(item -> {
            Voucher voucher = voucherRepository.findById(item.getVoucherId()).orElseThrow(() -> new IllegalArgumentException());
            return OrderItem.create(voucher.getId(), item.getQuantity(), voucher.getPrice());
        }).collect(Collectors.toList());

        Order order = Order.create(command.getOrdererId(), orderItems);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }
        Order saveOrder = orderRepository.save(order);
        orderEventPublisher.publish(new OrderCreatedEvent(1L));
        return 1L;
    }
}
