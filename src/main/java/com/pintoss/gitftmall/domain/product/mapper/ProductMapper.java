package com.pintoss.gitftmall.domain.product.mapper;

import com.pintoss.gitftmall.domain.product.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Page<ProductListResponse> toProductListResponseList(Page<Product> productPage) {
        return new PageImpl<>(
                productPage.stream()
                        .map(this::toProductListResponse)
                        .collect(Collectors.toList()),
                productPage.getPageable(),
                productPage.getTotalElements()
        );
    }

    private ProductListResponse toProductListResponse(Product product) {
        return new ProductListResponse(
                product.getId(),
                product.getName(),
                product.getDiscount() != null ? product.getDiscount().getCardDiscount() : null,
                product.getDiscount() != null ? product.getDiscount().getPhoneDiscount() : null,
                null // 이미지
        );
    }
}
