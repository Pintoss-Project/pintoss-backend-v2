package com.pintoss.gitftmall.domain.cart.infra.repository;

import com.pintoss.gitftmall.domain.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = c.quantity + :quantity WHERE c.userId = :userId AND c.id = :cartItemId AND c.isDeleted = false")
    int updateQuantity(@Param("userId") Long userId, @Param("cartItemId") Long cartItemId, @Param("quantity") int quantity);

}
