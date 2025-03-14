package com.pintoss.gitftmall.domain.order.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pintoss.gitftmall.domain.order.domain.vo.OrderStatus;
import com.pintoss.gitftmall.domain.order.domain.vo.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    private Long orderId;
    private String orderNo;
    private PaymentMethodType paymentMethodType;
    private OrderStatus orderStatus;
    private Long totalPrice;
    private String ordererName;
    private String ordererPhone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private List<OrderItemsResponse> orderItems;

    public OrderDetailResponse(Long orderId, String orderNo,
                               PaymentMethodType paymentMethodType, OrderStatus orderStatus,
                               Long totalPrice, String ordererName,
                               String ordererPhone, LocalDateTime orderDate) {
        this.orderDate = orderDate;
        this.ordererPhone = ordererPhone;
        this.ordererName = ordererName;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentMethodType = paymentMethodType;
        this.orderNo = orderNo;
        this.orderId = orderId;
    }

    public static OrderDetailResponse of(OrderDetailResponse orderDetail, List<OrderItemsResponse> orderItems) {
        return new OrderDetailResponse(
                orderDetail.getOrderId(),
                orderDetail.getOrderNo(),
                orderDetail.getPaymentMethodType(),
                orderDetail.getOrderStatus(),
                orderDetail.getTotalPrice(),
                orderDetail.getOrdererName(),
                orderDetail.getOrdererPhone(),
                orderDetail.getOrderDate(),
                orderItems
        );
    }
}
