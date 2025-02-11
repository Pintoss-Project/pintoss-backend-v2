package com.pintoss.gitftmall.domain.order.infra;

import com.pintoss.gitftmall.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
