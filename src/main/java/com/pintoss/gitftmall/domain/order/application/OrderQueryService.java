package com.pintoss.gitftmall.domain.order.application;

import com.pintoss.gitftmall.core.exceptions.ErrorCode;
import com.pintoss.gitftmall.core.exceptions.client.NotFoundException;
import com.pintoss.gitftmall.domain.order.controller.response.OrderDetailResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderItemsResponse;
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

    public List<OrderItemsResponse> getOrderItems(Long orderId) {
        return orderRepository.findOrderItems(orderId);
    }

    public OrderDetailResponse getOrderDetail(Long orderId) {
        OrderDetailResponse orderDetail = orderRepository.findOrderDetail(orderId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_ORDER));
        List<OrderItemsResponse> orderItems = orderRepository.findOrderItems(orderId);


        return OrderDetailResponse.of(orderDetail, orderItems);
    }
}
