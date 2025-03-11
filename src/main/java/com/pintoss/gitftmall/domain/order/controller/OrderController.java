package com.pintoss.gitftmall.domain.order.controller;

import com.pintoss.gitftmall.core.dto.ApiResponse;
import com.pintoss.gitftmall.core.util.SecurityContextUtils;
import com.pintoss.gitftmall.core.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.domain.membership.domain.vo.RoleEnum;
import com.pintoss.gitftmall.domain.order.application.OrderCreateService;
import com.pintoss.gitftmall.domain.order.application.OrderQueryService;
import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.request.OrderCreateRequest;
import com.pintoss.gitftmall.domain.order.controller.response.OrderListResponse;
import com.pintoss.gitftmall.domain.order.controller.response.OrderCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCreateService orderCreateService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    @AuthorizationRequired({RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
        Long userId = SecurityContextUtils.getUserId();
        OrderCreateServiceCommand command = OrderCreateServiceCommand.from(userId, request);

        OrderCreateResponse response = orderCreateService.create(command);

        return ApiResponse.ok(response);
    }

    @GetMapping
    @AuthorizationRequired({RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<List<OrderListResponse>> getOrders() {
        Long userId = SecurityContextUtils.getUserId();

        List<OrderListResponse> response = orderQueryService.getOrders(userId);

        return ApiResponse.ok(response);
    }
}
