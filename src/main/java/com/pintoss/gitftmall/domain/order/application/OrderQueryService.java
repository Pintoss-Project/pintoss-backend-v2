package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;

    // 주문 리스트 조회 기능
    public List<OrderListResponse> getOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

}
