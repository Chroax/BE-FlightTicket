package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.PaymentMethods;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodResponse {

    private Integer paymentId;
    private String paymentName;
    private String paymentType;
    private String ImagePath;

    public static PaymentMethodResponse build(PaymentMethods paymentMethods){
        return PaymentMethodResponse.builder()
                .paymentId(paymentMethods.getPaymentId())
                .paymentName(paymentMethods.getPaymentName())
                .paymentType(paymentMethods.getPaymentType())
                .ImagePath(paymentMethods.getImagePath())
                .build();
    }
}
