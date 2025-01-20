package com.pintoss.gitftmall.domain.product.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.product.application.ProductService;
import com.pintoss.gitftmall.domain.product.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.product.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final IProductRepository productRepository;
    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<ProductListResponse>> getProducts(
            @RequestParam(required = false) String category,
            @PageableDefault Pageable pageable
    ) {
        List<ProductListResponse> productInfoList;
        String message = "";

        if(category == null) {
            // 전체조회
            productInfoList = productService.getAllProducts(pageable);
            message = "전체 상품권 조회 완료";
        }
        else {
            // 카테고리 조회
            productInfoList = productService.getProductsByCategory(category, pageable);
            message = "카테고리 상품권 조회 완료";
        }
        return ApiResponse.of(HttpStatus.OK, message, productInfoList);
    }

    @GetMapping("/popular")
    public ApiResponse<List<ProductListResponse>> getPopularProducts(Pageable pageable) {
        List<ProductListResponse> productInfoList = productService.getPopularProducts(pageable);

        return ApiResponse.of(HttpStatus.OK, "인기 상품권 조회 완료", productInfoList);
    }
}
