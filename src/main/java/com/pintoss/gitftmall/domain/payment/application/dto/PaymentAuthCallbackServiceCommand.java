package com.pintoss.gitftmall.domain.payment.application.dto;

import com.pintoss.gitftmall.domain.payment.controller.dto.PaymentAuthCallbackRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentAuthCallbackServiceCommand {
    private String serviceId;
    private String serviceCode;
    private String orderId;
    private String orderDate;
    private String responseCode;
    private String responseMessage;
    private String detailResponseCode;
    private String detailResponseMessage;
    private String reserved1;
    private String reserved2;
    private String reserved3;
    private String message;
    private String checkSum;
    private String hashData;

    public static PaymentAuthCallbackServiceCommand from(PaymentAuthCallbackRequest request) {
        return new PaymentAuthCallbackServiceCommand(
                request.getSERVICE_ID(),
                request.getSERVICE_CODE(),
                request.getORDER_ID(),
                request.getORDER_DATE(),
                request.getRESPONSE_CODE(),
                request.getRESPONSE_MESSAGE(),
                request.getDETAIL_RESPONSE_CODE(),
                request.getDETAIL_RESPONSE_MESSAGE(),
                request.getRESERVED1(),
                request.getRESERVED2(),
                request.getRESERVED3(),
                request.getMESSAGE(),
                request.getCHECK_SUM(),
                request.getHASH_DATA()
        );
    }
}
