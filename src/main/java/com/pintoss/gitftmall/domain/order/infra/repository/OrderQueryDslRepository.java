package com.pintoss.gitftmall.domain.order.infra.repository;

import com.pintoss.gitftmall.domain.order.controller.response.OrderDetailResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderItemsResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.domain.vo.QOrderItem;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.pintoss.gitftmall.domain.membership.domain.QUser.user;
import static com.pintoss.gitftmall.domain.order.domain.QOrder.order;
import static com.pintoss.gitftmall.domain.order.domain.vo.QOrderItem.orderItem;
import static com.pintoss.gitftmall.domain.voucher.domain.QVoucher.voucher;

@Repository
@RequiredArgsConstructor
public class OrderQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<OrderListResponse> findOrdersByUserId(Long userId) {
        return queryFactory.select(
                Projections.constructor(OrderListResponse.class,
                        order.id.as("orderId"),
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

    public List<OrderItemsResponse> findOrderItems(Long orderId) {
        return queryFactory
                .select(Projections.constructor(OrderItemsResponse.class,
                            voucher.name.as("voucherName"),
                            orderItem.quantity,
                            orderItem.price,
                            Expressions.constant("발권완료"), // status 필드
                            Expressions.constant("1111111-1111111") // pinNum 필드
                ))
                .from(orderItem)
                .join(voucher).on(orderItem.voucherId.eq(voucher.id))
                .where(orderItem.order.id.eq(orderId))
                .fetch();
    }

    public Optional<OrderDetailResponse> findOrderDetail(Long orderId) {
        return Optional.ofNullable(
                queryFactory
                        .select(
                                Projections.constructor(OrderDetailResponse.class,
                                        order.id.as("orderId"),
                                        order.orderNo.value.as("orderNo"),
                                        order.paymentMethodType,
                                        order.status.as("orderStatus"),
                                        order.totalPrice,
                                        user.name.as("ordererName"),
                                        user.phone.phone.as("ordererPhone"),
                                        order.createdAt.as("orderDate")
                                ))
                        .from(order)
                        .innerJoin(user).on(user.id.eq(order.ordererId))
                        .where(order.id.eq(orderId))
                        .fetchOne()
        );
    }
}
