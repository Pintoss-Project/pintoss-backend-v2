package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.voucher.application.ProductService;
import com.pintoss.gitftmall.domain.voucher.controller.response.ProductListResponse;
import com.pintoss.gitftmall.domain.voucher.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final IProductRepository productRepository;
    private final ProductService productService;

    @GetMapping
    public ApiResponse<Page<ProductListResponse>> getProducts(
            @RequestParam(required = false) String category,
            @PageableDefault Pageable pageable
    ) {
        Page<ProductListResponse> productInfoList;
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
    public ApiResponse<Page<ProductListResponse>> getPopularProducts(Pageable pageable) {
        Page<ProductListResponse> productInfoList = productService.getPopularProducts(pageable);

        return ApiResponse.of(HttpStatus.OK, "인기 상품권 조회 완료", productInfoList);
    }
}
