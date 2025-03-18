package com.pintoss.gitftmall.domain.cart.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.util.SecurityContextUtils;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.cart.application.CartItemAddService;
import com.pintoss.gitftmall.domain.cart.application.CartItemDeleteService;
import com.pintoss.gitftmall.domain.cart.application.CartItemUpdateService;
import com.pintoss.gitftmall.domain.cart.application.CartItemQueryService;
import com.pintoss.gitftmall.domain.cart.application.dto.CartItemAddServiceCommand;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemAddRequest;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemListResponse;
import com.pintoss.gitftmall.domain.cart.controller.dto.CartItemUpdateRequest;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemQueryService cartItemQueryService;
    private final CartItemAddService cartItemAddService;
    private final CartItemUpdateService cartItemUpdateService;
    private final CartItemDeleteService cartItemDeleteService;

    @GetMapping("/items")
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<List<CartItemListResponse>> getCartItems() {
        Long userId = SecurityContextUtils.getUserId();

        List<CartItemListResponse> response = cartItemQueryService.getCartItemsByUserId(userId);
        return ApiResponse.ok(response);
    }

    @PostMapping("/items")
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<Void> addCartItem(@RequestBody List<CartItemAddRequest> cartItems) {
        Long userId = SecurityContextUtils.getUserId();

        CartItemAddServiceCommand command = CartItemAddServiceCommand.from(userId, cartItems);
        cartItemAddService.addCartItem(command);
        return ApiResponse.ok(null);
    }

    @PutMapping("/items/{cartItemId}")
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<Integer> updateCartItem(@PathVariable(value = "cartItemId") Long cartItemId,  @RequestBody CartItemUpdateRequest request) {
        Long userId = SecurityContextUtils.getUserId();

        int count = cartItemUpdateService.update(userId, cartItemId, request);

        return ApiResponse.ok(count);
    }

    @DeleteMapping("/items/{cartItemId}")
    @AuthorizationRequired({ RoleEnum.USER, RoleEnum.ADMIN })
    public ApiResponse<Void> deleteCartItem(@PathVariable(value = "cartItemId") Long cartItemId) {
        Long userId = SecurityContextUtils.getUserId();

        cartItemDeleteService.deleteCartItem(userId, cartItemId);
        return ApiResponse.ok(null);
    }
}
