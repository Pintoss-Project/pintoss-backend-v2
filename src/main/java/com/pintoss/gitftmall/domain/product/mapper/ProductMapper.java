package com.pintoss.gitftmall.domain.product.mapper;

import com.pintoss.gitftmall.domain.product.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public List<ProductListResponse> toProductListResponseList(Page<Product> productPage) {
        return productPage.stream()
                .map(this::toProductListResponse)
                .toList();
    }

    public ProductListResponse toProductListResponse(Product product) {
        return new ProductListResponse(
                product.getId(),
                product.getName(),
                product.getDiscount() != null ? product.getDiscount().getCardDiscount() : null,
                product.getDiscount() != null ? product.getDiscount().getPhoneDiscount() : null,
                null // 이미지
        );
    }
}
