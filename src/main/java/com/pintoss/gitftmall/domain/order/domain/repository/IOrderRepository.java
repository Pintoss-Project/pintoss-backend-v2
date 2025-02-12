package com.pintoss.gitftmall.domain.order.domain.repository;

import com.pintoss.gitftmall.domain.order.domain.Order;

public interface IOrderRepository {
    Order save(Order order);
}
