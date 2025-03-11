package com.pintoss.gitftmall.domain.cart.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.util.SecurityContextUtils;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.cart.application.CartAddService;
import com.pintoss.gitftmall.domain.cart.application.CartQueryService;
import com.pintoss.gitftmall.domain.cart.application.dto.CartAddServiceCommand;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemResponse;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemsAddRequest;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartAddService cartAddService;
    private final CartQueryService cartQueryService;

    @GetMapping
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<List<CartItemResponse>> getCartItems() {
        Long userId = SecurityContextUtils.getUserId();

        List<CartItemResponse> response = cartQueryService.getCartItems(userId);
        return ApiResponse.ok(response);
    }

    @PostMapping("/items")
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<Void> addCartItem(@RequestBody CartItemsAddRequest request) {
        Long userId = SecurityContextUtils.getUserId();

        CartAddServiceCommand command = CartAddServiceCommand.from(userId, request);
        cartAddService.addCartItem(command);
        return ApiResponse.ok(null);
    }
}
