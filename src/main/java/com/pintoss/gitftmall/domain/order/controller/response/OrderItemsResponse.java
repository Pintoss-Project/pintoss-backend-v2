package com.pintoss.gitftmall.domain.order.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsResponse {

    private String productName;
    private int quantity;
    private long price;
    private String status;
    private String pinNum;

}
