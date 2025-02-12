package com.pintoss.gitftmall.domain.order.infra.repository;

import com.pintoss.gitftmall.domain.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
