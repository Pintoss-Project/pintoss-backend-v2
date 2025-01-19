package com.pintoss.gitftmall.domain.product.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.product.application.ProductRegisterService;
import com.pintoss.gitftmall.domain.product.application.command.ProductRegisterServiceCommand;
import com.pintoss.gitftmall.domain.product.controller.request.ProductRegisterRequest;
import com.pintoss.gitftmall.domain.product.controller.request.ProductSearchRequest;
import com.pintoss.gitftmall.domain.product.controller.response.ProductSearchResponse;
import com.pintoss.gitftmall.domain.product.repository.IProductQueryRepository;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRegisterService productRegisterService;
    private final IProductQueryRepository productQueryRepository;

    @PostMapping
    @AuthorizationRequired(RoleEnum.ADMIN)
    public ApiResponse<Void> registerProduct(@RequestBody @Valid ProductRegisterRequest request){
        ProductRegisterServiceCommand command = new ProductRegisterServiceCommand(
                request.getName(),
                request.isPopular(),
                request.getCardDiscount(),
                request.getPhoneDiscount(),
                request.getHomePage(),
                request.getCsCenter(),
                request.getDescription(),
                request.getPublisher(),
                request.getLogoImageUrl(),
                request.getNote(),
                request.getIndex()
        );

        productRegisterService.register(command);

        return ApiResponse.ok(null);
    }

    @GetMapping("/search")
    public ApiResponse<Page<ProductSearchResponse>> search(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "category", required = false) String category,
        @PageableDefault Pageable pageable
    ) {
        ProductSearchRequest productSearchRequest = new ProductSearchRequest(keyword, category);
        Page<ProductSearchResponse> response = productQueryRepository.search(productSearchRequest, pageable);
        return ApiResponse.ok(response);
    }
}
