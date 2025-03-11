package com.pintoss.gitftmall.domain.order.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pintoss.gitftmall.domain.order.controller.request.PaymentMethodType;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse {

    private String orderNo;
    private OrderStatus status;
    private PaymentMethodType paymentMethodType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    private Long price;

}
