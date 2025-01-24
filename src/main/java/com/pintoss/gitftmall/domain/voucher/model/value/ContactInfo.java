package com.pintoss.gitftmall.domain.voucher.model.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContactInfo {

    @Column(length = 100)
    private HomePage homePage;

    @Column(length = 20)
    private CsCenter csCenter;

    public ContactInfo(HomePage homePage, CsCenter csCenter) {
        this.homePage = homePage;
        this.csCenter = csCenter;
    }

    public ContactInfo update(String homePage, String csCenter) {
        return new ContactInfo(
            homePage != null ? new HomePage(homePage) : this.homePage,
            csCenter != null ? new CsCenter(csCenter) : this.csCenter
        );
    }
}
