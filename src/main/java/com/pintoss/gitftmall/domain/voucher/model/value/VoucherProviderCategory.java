package com.pintoss.gitftmall.domain.voucher.model.value;

import com.pintoss.gitftmall.common.exceptions.voucher.InvalidCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoucherProviderCategory {

    CBM("문화/도서/영화"),
    GO("게임/온라인콘텐츠"),
    LS("생활/쇼핑");

    private final String category;

    public static VoucherProviderCategory from(String category) {
        try{
            return VoucherProviderCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("카테고리 오류: " + category + ". 사용 가능한 카테고리: [CBM(문화/도서/영화), GO(게임/온라인콘텐츠), LS(생활/쇼핑)]");
        }
    }
}
