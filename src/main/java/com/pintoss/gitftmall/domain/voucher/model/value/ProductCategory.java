package com.pintoss.gitftmall.domain.voucher.model.value;

import com.pintoss.gitftmall.common.exceptions.product.InvalidCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {

    CBM("문화/도서/영화"),
    GO("게임/온라인콘텐츠"),
    LS("생활/쇼핑");

    private final String category;

    public static ProductCategory from(String category) {
        try{
            return ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("해당 카테고리는 없는 카테고리입니다: " + category);
        }
    }
}
