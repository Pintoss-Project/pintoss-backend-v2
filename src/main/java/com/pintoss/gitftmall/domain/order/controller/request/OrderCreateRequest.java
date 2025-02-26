package com.pintoss.gitftmall.domain.order.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderCreateRequest {

    @NotNull(message = "결제 방법은 필수 입력 항목입니다.")
    private PaymentMethodType paymentMethod;
    @NotNull(message = "제공사 ID는 필수 입력 항목입니다.")
    private Long providerId;
    @NotEmpty(message = "주문 항목을 한 개 이상 포함해야 합니다.")
    private List<OrderItemRequest> orderItems;
}
