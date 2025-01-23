package com.pintoss.gitftmall.domain.voucher.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.voucher.application.ProductRegisterService;
import com.pintoss.gitftmall.domain.voucher.application.command.ProductRegisterServiceCommand;
import com.pintoss.gitftmall.domain.voucher.controller.request.ProductRegisterRequest;
import com.pintoss.gitftmall.infra.security.interceptor.AuthorizationRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class AdminProductController {

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
