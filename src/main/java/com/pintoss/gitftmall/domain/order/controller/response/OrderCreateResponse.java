package com.pintoss.gitftmall.domain.order.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderCreateResponse {
    private Long orderId;

    private String paymentMethod;

    private Long price;

    private String ordererName;

    private String productCode;

    private String providerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
}
