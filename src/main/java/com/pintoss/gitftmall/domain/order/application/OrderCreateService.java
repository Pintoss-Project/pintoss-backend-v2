package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.membership.domain.User;
import com.pintoss.gitftmall.domain.membership.domain.repository.UserRepository;
import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.response.OrderCreateResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import com.pintoss.gitftmall.domain.voucherProvider.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucherProvider.domain.repository.VoucherProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;
    private final VoucherProviderRepository voucherProviderRepository;
    private final UserRepository userRepository;

    public OrderCreateResponse create(OrderCreateServiceCommand command) {
        User user = userRepository.findById(command.getOrdererId()).get();

        VoucherProvider provider = voucherProviderRepository.findById(command.getProviderId()).get();
        List<OrderItem> orderItems = command.getOrderItems().stream().map(item -> {
                Voucher voucher = voucherRepository.findById(item.getVoucherId()).orElseThrow(() -> new IllegalArgumentException());
                voucher.validateStockForOrder(item.getQuantity());
                return OrderItem.create(voucher.getId(), item.getQuantity(), voucher.getPrice());
        }).collect(Collectors.toList());

        Order order = Order.create(command.getOrdererId(), user.getName(), provider.getCode(), provider.getName(), orderItems, command.getPaymentMethodType());

        Order saveOrder = orderRepository.save(order);

        return new OrderCreateResponse(
                saveOrder.getId(),
                command.getPaymentMethodType().getServiceCode(),
                saveOrder.getTotalPrice(),
                saveOrder.getOrdererName(),
                saveOrder.getProductCode(),
                saveOrder.getProductName(),
                saveOrder.getCreatedAt()
        );
    }
}
