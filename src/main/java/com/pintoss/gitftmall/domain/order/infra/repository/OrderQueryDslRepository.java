package com.pintoss.gitftmall.domain.order.infra.repository;

import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pintoss.gitftmall.domain.order.domain.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<OrderListResponse> findOrdersByUserId(Long userId) {
        return queryFactory.select(
                Projections.constructor(OrderListResponse.class,
                        order.orderNo.value.as("orderNo"),
                        order.status,
                        order.paymentMethodType,
                        order.createdAt.as("orderDate"),
                        order.totalPrice.as("price")
                ))
                .from(order)
                .where(order.ordererId.eq(userId))
                .fetch();
    }
}
