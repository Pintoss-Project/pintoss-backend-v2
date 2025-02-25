package com.pintoss.gitftmall.domain.order.controller.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreateResponse {
    private Long orderId;

    private Long price;

    private String ordererName;

    private String voucherProvider;

    private LocalDateTime orderDate;
}
