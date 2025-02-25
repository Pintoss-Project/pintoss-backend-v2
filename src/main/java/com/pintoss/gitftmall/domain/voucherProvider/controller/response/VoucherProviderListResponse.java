package com.pintoss.gitftmall.domain.voucherProvider.controller.response;

import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.ContactInfo;
import com.pintoss.gitftmall.domain.voucherProvider.domain.vo.Discount;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoucherProviderListResponse {

    private Long id;
    private String name;
    private boolean isPopular;
    private Discount discount;
    private ContactInfo contactInfo;
    private String description;
    private String publisher;
    private String imageUrl;
    private String note;

    @QueryProjection
    public VoucherProviderListResponse(Long id, String name, boolean isPopular, Discount discount,
        ContactInfo contactInfo, String description, String publisher, String imageUrl, String note) {
        this.id = id;
        this.name = name;
        this.isPopular = isPopular;
        this.discount = discount;
        this.contactInfo = contactInfo;
        this.description = description;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
        this.note = note;
    }
}
