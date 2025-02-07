package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.model.Order;
import com.pintoss.gitftmall.domain.order.model.value.OrderItem;
import com.pintoss.gitftmall.domain.order.repository.IOrderRepository;
import com.pintoss.gitftmall.domain.voucher.model.Voucher;
import com.pintoss.gitftmall.domain.voucher.repository.IVoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final IOrderRepository orderRepository;
    private final IVoucherRepository voucherRepository;

    public Long create(OrderCreateServiceCommand command) {
        List<OrderItem> orderItems = command.getOrderItems().stream().map(item -> {
            Voucher voucher = voucherRepository.findById(item.getVoucherId()).orElseThrow(() -> new IllegalArgumentException());
            return OrderItem.create(voucher.getId(), item.getQuantity(), voucher.getPrice());
        }).collect(Collectors.toList());

        Order order = Order.create(command.getOrdererId(), orderItems);

        return orderRepository.save(order).getId();
    }
}
