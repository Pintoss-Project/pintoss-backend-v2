package com.pintoss.gitftmall.domain.voucher.domain;

import com.pintoss.gitftmall.core.exceptions.client.BadRequestException;
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

    private String name;

    private Long price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Voucher(Long voucherProviderId, String name, Long price, Integer stock) {
        validateInitialStock(stock);
        this.voucherProviderId = voucherProviderId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Voucher create(Long voucherProviderId, String name, Long price, Integer stock) {
        return new Voucher(voucherProviderId, name, price, stock);
    }

    // 1. create 시 , 1개이상 입력했는지 검증하는 도메인 로직
    private void validateInitialStock(int stock) {
        if( stock < 1 ) {
            throw new BadRequestException("상품권 초기 재고는 1개 이상이어야 합니다.");
        }
    }

    // 2. 결제 진행 시 재고 감소 할 때, 재고 수량이 구매 수량보다 많은지 검증하는 도메인 로직
    public void validateStockForPurchase(int purchaseQuantity) {
        if (purchaseQuantity < 1) {
            throw new BadRequestException("구매 수량은 1개 이상이어야 합니다.");
        }
        if (this.stock < purchaseQuantity) {
            throw new BadRequestException("재고가 부족하여 결제를 진행할 수 없습니다.");
        }
    }

    // 3. 주문 진행 시 재고 확인 할 때, 재고 수량이 주문 수량보다 많은지 검증하는 도메인 로직
    public void validateStockForOrder(int orderQuantity) {
        if(orderQuantity < 1) {
            throw new BadRequestException("주문 수량은 1개 이상이어야합니다.");
        }
        if(this.stock < orderQuantity) {
            throw new BadRequestException("재고가 부족하여 주문을 진행할 수 없습니다.");
        }
    }

    // 재고 감소 (주문 시 사용)
    public void reduceStock(int quantity) {
        validateStockForOrder(quantity);
        this.stock -= quantity;
        this.updatedAt = LocalDateTime.now();
    }

    // 🎯 재고 증가 (환불 또는 공급)
    public void increaseStock(int quantity) {
        if (quantity < 1) {
            throw new BadRequestException("추가할 재고는 1개 이상이어야 합니다.");
        }
        this.stock += quantity;
        this.updatedAt = LocalDateTime.now();
    }
}
