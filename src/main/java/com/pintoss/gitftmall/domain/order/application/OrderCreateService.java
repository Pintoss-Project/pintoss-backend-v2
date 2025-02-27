package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.response.OrderCreateResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import com.pintoss.gitftmall.domain.voucher.domain.VoucherProvider;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherProviderRepository;
import com.pintoss.gitftmall.domain.voucher.domain.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;
    private final VoucherProviderRepository voucherProviderRepository;

    public OrderCreateResponse create(OrderCreateServiceCommand command) {
        Set<String> productNames = new HashSet<>();
        // TODO 1 : Product Code는 무엇인가 ?
        // TODO 2 : ACL 계층 생성 예정
        VoucherProvider provider = voucherProviderRepository.findById(command.getProviderId()).get();

        List<OrderItem> orderItems = command.getOrderItems().stream().map(item -> {
                Voucher voucher = voucherRepository.findById(item.getVoucherId()).orElseThrow(() -> new IllegalArgumentException());
                voucher.validateStockForOrder(item.getQuantity());
                voucher.reduceStock(item.getQuantity());
                productNames.add(voucher.getName());
                return OrderItem.create(voucher.getId(), item.getQuantity(), voucher.getPrice());
        }).collect(Collectors.toList());

        
        // TODO : 상품 이름이 어떤 값을 의미하는 지 물어보기
        // 대표 상품권을 선택하고 나머지 개수를 계산
        String productName = productNames.stream()
            .findFirst()
            .map(firstProduct -> {
                int remainingCount = productNames.size() - 1;
                return remainingCount > 0 ? firstProduct + " 외 " + remainingCount + "개" : firstProduct;
            })
            .orElse("상품 없음");


        Order order = Order.create(command.getOrdererId(), provider.getCode(), productName, orderItems, command.getPaymentMethodType());

        // TODO : 이벤트 발행 예정
        Order saveOrder = orderRepository.save(order);

        return new OrderCreateResponse(
                saveOrder.getId(),
                saveOrder.getOrdererId(),
                command.getPaymentMethodType().getServiceCode(),
                saveOrder.getTotalPrice(),
                saveOrder.getProductCode(),
                saveOrder.getProductName(),
                saveOrder.getCreatedAt()
        );
    }
}
