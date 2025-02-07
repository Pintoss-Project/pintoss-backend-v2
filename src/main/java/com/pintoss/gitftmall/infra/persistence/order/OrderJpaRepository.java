package com.pintoss.gitftmall.infra.persistence.order;

import com.pintoss.gitftmall.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
