package com.pintoss.gitftmall.domain.product.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.product.application.ProductRegisterService;
import com.pintoss.gitftmall.domain.product.application.command.ProductRegisterServiceCommand;
import com.pintoss.gitftmall.domain.product.controller.request.ProductRegisterRequest;
import com.pintoss.gitftmall.infra.config.web.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRegisterService productRegisterService;

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



}
