package com.pintoss.gitftmall.domain.order.infra.repository;

import com.pintoss.gitftmall.domain.order.controller.response.OrderDetailResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderItemsResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.Order;
import com.pintoss.gitftmall.domain.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderQueryDslRepository orderQueryDslRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public List<OrderListResponse> findOrdersByUserId(Long userId) {
        return orderQueryDslRepository.findOrdersByUserId(userId);
    }

    @Override
    public List<OrderItemsResponse> findOrderItems(Long orderId) {
        return orderQueryDslRepository.findOrderItems(orderId);
    }

    @Override
    public Optional<OrderDetailResponse> findOrderDetail(Long orderId) {
        return orderQueryDslRepository.findOrderDetail(orderId);
    }
}
