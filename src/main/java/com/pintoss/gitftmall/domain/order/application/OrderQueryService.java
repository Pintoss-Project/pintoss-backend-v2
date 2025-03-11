package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.controller.request.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private OrderRepository orderRepository;

    // 주문 리스트 조회 기능
    public OrderListResponse getOrders(Long userId) {
        return orderRepository.findBy;
    }

}
