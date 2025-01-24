package com.pintoss.gitftmall.domain.voucher.model;

import com.pintoss.gitftmall.domain.voucher.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoucherProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_provider_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private boolean isPopular;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "homePage", column = @Column(name = "homePage", nullable = false)),
            @AttributeOverride(name = "csCenter", column = @Column(name = "csCenter", nullable = false)),
    })
    private ContactInfo contactInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cardDiscount", column = @Column(name = "cardDiscount", nullable = false)),
            @AttributeOverride(name = "phoneDiscount", column = @Column(name = "phoneDiscount", nullable = false))
    })
    private Discount discount;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 30, nullable = false)
    private String publisher;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String note;

    //드래그 드랍 인덱스 컬럼
    @Column(name = "voucher_provider_index")
    private int index;

    @Enumerated(EnumType.STRING)
    private VoucherProviderCategory category;

    private String logoImageUrl;

    @OneToMany(mappedBy = "voucherProvider")
    private final List<Voucher> voucherList = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void addVoucher(Voucher voucher) {
        voucherList.add(voucher);
        voucher.setVoucherProvider(this);
    }

    private VoucherProvider(
            String name, boolean isPopular, ContactInfo contactInfo, Discount discount, String description,
            String publisher, VoucherProviderCategory category, String logoImageUrl, String note, int index
    ) {
        this.name = name;
        this.isPopular = isPopular;
        this.contactInfo = contactInfo;
        this.discount = discount;
        this.description = description;
        this.publisher = publisher;
        this.category = category;
        this.logoImageUrl = logoImageUrl;
        this.note = note;
        this.index = index;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static VoucherProvider create(
            String name, boolean isPopular, ContactInfo contactInfo, Discount discount, String description, String publisher,
            VoucherProviderCategory category, String logoImageUrl, String note, int index
    ){
        return new VoucherProvider(
                name,
                isPopular,
                contactInfo,
                discount,
                description,
                publisher,
                category,
                logoImageUrl,
                note,
                index
        );
    }
}
