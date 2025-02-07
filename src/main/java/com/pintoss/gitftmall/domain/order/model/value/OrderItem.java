package com.pintoss.gitftmall.domain.order.model.value;

import com.pintoss.gitftmall.domain.order.model.Order;
import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    @Column(nullable = false, name = "voucher_id")
    private Long voucherId;

    private Integer quantity;

    private Long price;

    public Long calcTotalPrice() {
        return quantity * price;
    }

    private OrderItem(Long voucherId, Integer quantity, Long price) {
        this.voucherId = voucherId;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem create(Long voucherId, Integer quantity, Long price) {
        return new OrderItem(voucherId, quantity, price);
    }
}
