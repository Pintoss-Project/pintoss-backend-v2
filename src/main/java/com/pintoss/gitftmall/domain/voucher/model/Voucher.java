package com.pintoss.gitftmall.domain.voucher.model;

import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long id;

    private String name;

    private BigDecimal price;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_provider_id")
    private VoucherProvider voucherProvider;

    private LocalDateTime createdAt;

    public void setVoucherProvider(VoucherProvider voucherProvider) {
        this.voucherProvider = voucherProvider;
    }

    public Voucher(String name, BigDecimal price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
    }

    public static Voucher create(
            String name, BigDecimal price, int stock
    ) {
        return new Voucher(
                name,
                price,
                stock
        );
    }

    public void updateStock(int stock) {
        this.stock = stock;
    }
}
