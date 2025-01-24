package com.pintoss.gitftmall.domain.voucher.model;

import com.pintoss.gitftmall.domain.voucher.model.value.ContactInfo;
import com.pintoss.gitftmall.domain.voucher.model.value.Discount;
import com.pintoss.gitftmall.domain.voucher.model.value.VoucherProviderCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(mappedBy = "voucherProvider", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public void update(
            Optional<String> name, Optional<Boolean> isPopular,
            Optional<String> homePage, Optional<String> csCenter,
            BigDecimal cardDiscount, BigDecimal phoneDiscount,
            Optional<String> description, Optional<String> publisher,
            Optional<String> logoImageUrl, Optional<String> note
    ) {
        name.ifPresent(newName -> this.name = newName);
        isPopular.ifPresent(newIsPopular -> this.isPopular = newIsPopular);
        if (homePage.isPresent() || csCenter.isPresent()) {
            this.contactInfo = this.contactInfo.update(
                homePage.orElse(this.contactInfo.getHomePage().getUrl()),
                csCenter.orElse(this.contactInfo.getCsCenter().getTel())
            );
        }
        if (!cardDiscount.equals(BigDecimal.ZERO) || !phoneDiscount.equals(BigDecimal.ZERO)) {
            BigDecimal updatedCardDiscount = !cardDiscount.equals(BigDecimal.ZERO)
                    ? cardDiscount
                    : this.discount.getCardDiscount();
            BigDecimal updatedPhoneDiscount = !phoneDiscount.equals(BigDecimal.ZERO)
                    ? phoneDiscount
                    : this.discount.getPhoneDiscount();
            this.discount = this.discount.update(updatedCardDiscount, updatedPhoneDiscount);
        }
        description.ifPresent(newDescription -> this.description = newDescription);
        publisher.ifPresent(newPublisher -> this.publisher = newPublisher);
        logoImageUrl.ifPresent(newLogoImageUrl -> this.logoImageUrl = newLogoImageUrl);
        note.ifPresent(newNote -> this.note = newNote);
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
