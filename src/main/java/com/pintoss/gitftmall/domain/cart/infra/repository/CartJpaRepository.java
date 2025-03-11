package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMemberId(Long userId);
}
