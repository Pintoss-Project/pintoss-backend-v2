package com.pintoss.gitftmall.domain.payment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAuthCallbackRequest {

    private String SERVICE_ID;
    private String SERVICE_CODE;
    private String ORDER_ID;
    private String ORDER_DATE;
    private String RESPONSE_CODE;
    private String RESPONSE_MESSAGE;
    private String DETAIL_RESPONSE_CODE;
    private String DETAIL_RESPONSE_MESSAGE;
    private String RESERVED1;
    private String RESERVED2;
    private String RESERVED3;
    private String MESSAGE;
    private String CHECK_SUM;
    private String HASH_DATA;

}

