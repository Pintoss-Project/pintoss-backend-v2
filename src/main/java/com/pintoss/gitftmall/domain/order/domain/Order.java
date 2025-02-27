package com.pintoss.gitftmall.domain.order.domain;

import com.pintoss.gitftmall.domain.order.controller.request.PaymentMethodType;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderItem;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO : 값 객체 생성 예정
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

    @Column(nullable = false, name = "product_code")
    private String productCode;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

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

    private Order(Long ordererId, String productCode, String productName,List<OrderItem> orderItems, PaymentMethodType paymentMethodType) {
        this.ordererId = ordererId;
        this.productCode = productCode;
        this.productName = productName;
        orderItems.forEach(this::addOrderItem); // 연관관계 메서드 사용
        this.orderItems = orderItems;
        this.status = OrderStatus.PENDING;
        this.totalPrice = orderItems.stream().mapToLong(item -> item.calculateTotalPrice()).sum();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 개별 OrderItem 추가 메서드 (연관관계 설정)
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order create(Long ordererId, String productCode, String productName, List<OrderItem> orderItems, PaymentMethodType paymentMethodType) {
        return new Order(ordererId, productCode, productName, orderItems, paymentMethodType);
    }
}
