package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.core.exceptions.client.BadRequestException;
import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.request.OrderItemRequest;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;
    private final VoucherProviderRepository voucherProviderRepository;
    private final OrderItemFactory orderItemFactory;

    public OrderCreateResponse create(OrderCreateServiceCommand command) {
        List<Long> voucherIds = command.getOrderItems().stream()
                        .map(OrderItemRequest::getVoucherId)
                        .toList();
        List<Voucher> vouchers = voucherRepository.findAllByIds(voucherIds);

        List<OrderItem> orderItems = orderItemFactory.validateAndCreateOrderItems(vouchers, command.getOrderItems());

        Set<Long> providerIds = vouchers.stream()
                .map(Voucher::getVoucherProviderId)
                .collect(Collectors.toSet());

        VoucherProvider voucherProvider = voucherProviderRepository.findById(providerIds.iterator().next()).orElseThrow(
                () -> new BadRequestException("존재하지 않는 상품 제공사입니다.")
        );

        Order order = Order.create(
                command.getOrdererId(),
                generateProductName(voucherProvider, providerIds),
                orderItems,
                command.getPaymentMethodType()
        );

        Order saveOrder = orderRepository.save(order);

        return new OrderCreateResponse(
                saveOrder.getOrderNo().getValue(),
                saveOrder.getOrdererId(),
                command.getPaymentMethodType().getServiceCode(),
                saveOrder.getTotalPrice(),
                saveOrder.getProductName(),
                saveOrder.getCreatedAt()
        );
    }

    private String generateProductName(VoucherProvider voucherProvider, Set<Long> providerIds) {
        if(voucherProvider == null){
            throw new BadRequestException("존재하지 않는 상품 제공사입니다.");
        }
        if(providerIds.size() == 1) {
            return voucherProvider.getName();
        }
        return voucherProvider.getName()+" 외 "+ (providerIds.size() -1)+"건";
    }
}
