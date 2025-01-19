package com.pintoss.gitftmall.domain.product.model.value;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContactInfo {

    @AttributeOverrides({
        @AttributeOverride(name = "url", column = @Column(name = "homePage", nullable = false, length = 100))
    })
    private HomePage homePage;

    @AttributeOverrides({
        @AttributeOverride(name = "tel", column = @Column(name = "csCenter", nullable = false, length = 20))
    })
    private CsCenter csCenter;

    public ContactInfo(HomePage homePage, CsCenter csCenter) {
        this.homePage = homePage;
        this.csCenter = csCenter;
    }
}
