package com.pintoss.gitftmall.domain.order.repository;

import com.pintoss.gitftmall.domain.order.model.Order;

public interface IOrderRepository {
    Order save(Order order);
}
