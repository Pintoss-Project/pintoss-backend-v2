package com.pintoss.gitftmall.domain.order.model;

import com.pintoss.gitftmall.domain.order.model.value.OrderItem;
import com.pintoss.gitftmall.domain.order.model.value.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "orderer_id")
    private Long ordererId;

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long totalPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Order(Long ordererId, List<OrderItem> orderItems) {
        this.ordererId = ordererId;
        this.orderItems = orderItems;
        this.status = OrderStatus.PENDING;
        this.totalPrice = orderItems.stream().mapToLong(item -> item.calcTotalPrice()).sum();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Order create(Long ordererId, List<OrderItem> orderItems) {
        return new Order(ordererId, orderItems);
    }
}
