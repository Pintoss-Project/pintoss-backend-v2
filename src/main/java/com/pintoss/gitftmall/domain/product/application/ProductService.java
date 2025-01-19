package com.pintoss.gitftmall.domain.product.application;

import com.pintoss.gitftmall.common.exceptions.product.InvalidCategoryException;
import com.pintoss.gitftmall.domain.product.controller.response.ProductInfoResponse;
import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.model.value.ProductCategory;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public List<ProductInfoResponse> getProductsByCategory(String category, Pageable pageable) {
        ProductCategory productCategory = getProductCategory(category);

        Page<Product> productPage = productRepository.findByCategory(productCategory, pageable);

        return mapToProductInfoResponseList(productPage);
    }

    public List<ProductInfoResponse> mapToProductInfoResponseList(Page<Product> productPage) {
        return productPage.stream()
                .map(product -> new ProductInfoResponse(
                        product.getId(),
                        product.getName(),
                        product.getDiscount() != null ? product.getDiscount().getCardDiscount() : null,
                        product.getDiscount() != null ? product.getDiscount().getPhoneDiscount() : null,
                        null
                ))
                .toList();
    }

    public ProductCategory getProductCategory(String category) {
        try{
            return ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException(category + " 는 없는 카테고리입니다");
        }
    }
}
