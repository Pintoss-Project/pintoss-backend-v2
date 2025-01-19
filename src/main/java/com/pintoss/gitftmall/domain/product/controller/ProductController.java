package com.pintoss.gitftmall.domain.product.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.product.controller.response.ProductInfoResponse;
import com.pintoss.gitftmall.domain.product.model.Product;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final IProductRepository productRepository;

    @GetMapping
    public ApiResponse<List<ProductInfoResponse>> getProducts(@PageableDefault Pageable pageable) {
        Page<Product> productList = productRepository.getProducts(pageable);

        List<ProductInfoResponse> productInfoList = productList.stream()
                .map(product -> new ProductInfoResponse(
                        product.getId(),
                        product.getName(),
                        product.getDiscount() != null ? product.getDiscount().getCardDiscount() : null,
                        product.getDiscount() != null ? product.getDiscount().getPhoneDiscount() : null,
                        null
                ))
                .toList();

        return ApiResponse.of(HttpStatus.OK, "전체 상품권 조회 완료", productInfoList);
    }

    @GetMapping("/popular")
    public ApiResponse<List<ProductInfoResponse>> getPopularProducts(Pageable pageable) {
        Page<Product> popularProductList = productRepository.getPopularProducts(pageable);

        List<ProductInfoResponse> popularProductInfoList = popularProductList.stream()
                .map(product -> new ProductInfoResponse(
                        product.getId(),
                        product.getName(),
                        product.getDiscount() != null ? product.getDiscount().getCardDiscount() : null,
                        product.getDiscount() != null ? product.getDiscount().getPhoneDiscount() : null,
                        null
                ))
                .toList();

        return ApiResponse.of(HttpStatus.OK, "인기 상품권 조회 완료", popularProductInfoList);
    }
}
