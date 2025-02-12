package com.pintoss.gitftmall.domain.order.domain.repository;

import com.pintoss.gitftmall.domain.order.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
