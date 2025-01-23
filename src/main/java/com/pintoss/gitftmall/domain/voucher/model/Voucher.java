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
    @Column(name = "voucher_id")
    private Long id;

    private String name;

    private Long price;

    private int stock;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cardDiscount", column = @Column(name = "cardDiscount", nullable = false)),
            @AttributeOverride(name = "phoneDiscount", column = @Column(name = "phoneDiscount", nullable = false))
    })
    private Discount discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_provider_id")
    private VoucherProvider voucherProvider;

    private LocalDateTime createdAt;
}
