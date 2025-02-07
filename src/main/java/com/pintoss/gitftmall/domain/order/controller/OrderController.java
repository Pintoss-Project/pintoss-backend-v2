package com.pintoss.gitftmall.domain.order.controller;

import com.pintoss.gitftmall.common.dto.ApiResponse;
import com.pintoss.gitftmall.domain.membership.model.value.RoleEnum;
import com.pintoss.gitftmall.domain.order.application.OrderCreateService;
import com.pintoss.gitftmall.domain.order.application.command.OrderCreateServiceCommand;
import com.pintoss.gitftmall.domain.order.controller.request.OrderCreateRequest;
import com.pintoss.gitftmall.domain.order.controller.response.OrderCreateResponse;
import com.pintoss.gitftmall.infra.config.web.interceptor.AuthorizationRequired;
import com.pintoss.gitftmall.infra.util.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCreateService orderCreateService;

    @PostMapping()
    @AuthorizationRequired({RoleEnum.USER, RoleEnum.ADMIN})
    public ApiResponse<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
        Long userId = SecurityContextUtils.getUserId();
        OrderCreateServiceCommand command = new OrderCreateServiceCommand(userId, request.getOrderItems());

        Long orderId = orderCreateService.create(command);

        return ApiResponse.ok(new OrderCreateResponse(orderId));
    }
}
