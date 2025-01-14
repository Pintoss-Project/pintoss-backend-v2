package com.pintoss.gitftmall.domain.product.model;

import com.pintoss.gitftmall.domain.product.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.product.model.value.Discount;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

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

    private Product(String name, boolean isPopular, Discount discount, ContactInfo contactInfo, String description, String publisher, String note, int index) {
        this.name = name;
        this.isPopular = isPopular;
        this.discount = discount;
        this.contactInfo = contactInfo;
        this.description = description;
        this.publisher = publisher;
        this.note = note;
        this.index = index;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Product create(String name, boolean isPopular, Discount discount, ContactInfo contactInfo, String description, String publisher,
                                 String note, int index){
        return new Product(
                name,
                isPopular,
                discount,
                contactInfo,
                description,
                publisher,
                note,
                index
        );
    }
}
