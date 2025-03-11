package com.pintoss.gitftmall.domain.order.domain.repository;

import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);

    List<OrderListResponse> findOrdersByUserId(Long userId);
}
