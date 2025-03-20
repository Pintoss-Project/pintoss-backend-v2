package com.pintoss.gitftmall.domain.order.domain.service;

import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.voucher.domain.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VoucherOrderValidator {

    // 주문될 상품 목록
    // 주문될 주문 상품 목록
    public void validateStockForOrder(List<OrderItem> orderItems, List<Voucher> vouchers) {
        Map<Long, Voucher> voucherMap = vouchers.stream()
                .collect(Collectors.toMap(Voucher::getId, Function.identity()));

        orderItems.stream()
                .forEach(orderItem -> {
                    Voucher voucher = voucherMap.get(orderItem.getVoucherId());
                    orderItem.validatePriceMatch(voucher.getPrice()); // 실제 가격과 주문 가격이 일치하는지 확인하는 도메인 로직인데 네이밍 적절한지 확인해줘
                    voucher.validateStockForOrder(orderItem.getQuantity());
                    voucher.reduceStock(orderItem.getQuantity());
                });
    }
}
