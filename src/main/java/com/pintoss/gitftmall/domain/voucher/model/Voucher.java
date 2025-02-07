package com.pintoss.gitftmall.domain.voucher.model;

import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "voucher_provider_id")
    private Long voucherProviderId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cardDiscount", column = @Column(name = "cardDiscount", nullable = false)),
            @AttributeOverride(name = "phoneDiscount", column = @Column(name = "phoneDiscount", nullable = false))
    })
    private Discount discount;

    private Long price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Voucher(Long voucherProviderId, Discount discount, Long price, Integer stock) {
        this.voucherProviderId = voucherProviderId;
        this.discount = discount;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Voucher create(Long voucherProviderId, Discount discount, Long price, Integer stock) {
        return new Voucher(voucherProviderId, discount, price, stock);
    }
}
