package com.pintoss.gitftmall.infra.persistence.order;

import com.pintoss.gitftmall.domain.order.model.Order;
import com.pintoss.gitftmall.domain.order.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements IOrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }
}
