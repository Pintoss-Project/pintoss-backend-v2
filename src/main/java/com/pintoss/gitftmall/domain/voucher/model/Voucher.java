package com.pintoss.gitftmall.domain.voucher.model;

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

    private Long price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Voucher(Long voucherProviderId, Long price, Integer stock) {
        this.voucherProviderId = voucherProviderId;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Voucher create(Long voucherProviderId, Long price, Integer stock) {
        return new Voucher(voucherProviderId, price, stock);
    }
}
