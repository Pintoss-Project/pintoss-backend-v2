package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.controller.request.OrderItemRequest;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemFactory {

    public List<OrderItem> validateAndCreateOrderItems(List<Voucher> vouchers, List<OrderItemRequest> orderItems) {
        Map<Long, Voucher> voucherMap = vouchers.stream()
                .collect(Collectors.toMap(Voucher::getId, Function.identity()));

        return orderItems.stream()
                .map(item -> {
                    Voucher voucher = voucherMap.get(item.getVoucherId());

                    if (voucher == null) {
                        throw new IllegalArgumentException("상품권이 존재하지 않습니다. ID: " + item.getVoucherId());
                    }

                    voucher.validateStockForOrder(item.getQuantity());
                    voucher.reduceStock(item.getQuantity());

                    return OrderItem.create(voucher.getId(), item.getQuantity(), voucher.getPrice());
                })
                .collect(Collectors.toList());
    }
}
