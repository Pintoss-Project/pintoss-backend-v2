package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.request.OrderItemRequest;
import com.pintoss.gitftmall.domain.order.controller.response.OrderCreateResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;
    private final OrderItemFactory orderItemFactory;

    public OrderCreateResponse create(OrderCreateServiceCommand command) {
        List<Long> voucherIds = command.getOrderItems().stream()
                        .map(OrderItemRequest::getVoucherId)
                        .toList();
        List<Voucher> vouchers = voucherRepository.findAllByIds(voucherIds);

        Map<Long, Voucher> voucherMap = vouchers.stream()
                .collect(Collectors.toMap(Voucher::getId, Function.identity()));


        List<OrderItem> orderItems = orderItemFactory.validateAndCreateOrderItems(vouchers, command.getOrderItems());

        Order order = Order.create(command.getOrdererId(),"상품 이름...", orderItems, command.getPaymentMethodType());

        // TODO : 이벤트 발행 예정
        Order saveOrder = orderRepository.save(order);

        return new OrderCreateResponse(
                saveOrder.getId(),
                saveOrder.getOrdererId(),
                command.getPaymentMethodType().getServiceCode(),
                saveOrder.getTotalPrice(),
                saveOrder.getProductName(),
                saveOrder.getCreatedAt()
        );
    }
}

// 대표 상품권을 선택하고 나머지 개수를 계산
//String productName = productNames.stream()
//        .findFirst()
//        .map(firstProduct -> {
//            int remainingCount = productNames.size() - 1;
//            return remainingCount > 0 ? firstProduct + " 외 " + remainingCount + "개" : firstProduct;
//        })
//        .orElse("상품 없음");