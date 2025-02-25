package com.pintoss.gitftmall.domain.voucherProvider.domain;

import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.Discount;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.ContactInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoucherProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private boolean isPopular;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "cardDiscount", column = @Column(name = "cardDiscount", nullable = false)),
        @AttributeOverride(name = "phoneDiscount", column = @Column(name = "phoneDiscount", nullable = false))
    })
    private Discount discount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "homePage", column = @Column(name = "homePage", nullable = false)),
            @AttributeOverride(name = "csCenter", column = @Column(name = "csCenter", nullable = false)),
    })
    private ContactInfo contactInfo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 30, nullable = false)
    private String publisher;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String note;

    //드래그 드랍 인덱스 컬럼
    @Column(name = "product_index")
    private int index;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String imageUrl;

    private VoucherProvider(String name, boolean isPopular, Discount discount, ContactInfo contactInfo, String description, String publisher, String note, int index, String imageUrl) {
        this.name = name;
        this.isPopular = isPopular;
        this.discount = discount;
        this.contactInfo = contactInfo;
        this.description = description;
        this.publisher = publisher;
        this.note = note;
        this.index = index;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static VoucherProvider create(String name, boolean isPopular, Discount discount, ContactInfo contactInfo, String description, String publisher,
                                 String note, int index, String imageUrl){
        return new VoucherProvider(
                name,
                isPopular,
                discount,
                contactInfo,
                description,
                publisher,
                note,
                index,
                imageUrl
        );
    }
}
