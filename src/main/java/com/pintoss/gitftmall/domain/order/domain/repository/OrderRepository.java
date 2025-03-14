package com.pintoss.gitftmall.domain.order.domain.repository;

import com.pintoss.gitftmall.domain.order.controller.response.OrderDetailResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderItemsResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    List<OrderListResponse> findOrdersByUserId(Long userId);

    List<OrderItemsResponse> findOrderItems(Long orderId);

    Optional<OrderDetailResponse> findOrderDetail(Long orderId);
}
