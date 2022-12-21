package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.PaymentMethodRequest;
import com.binar.finalproject.BEFlightTicket.dto.PaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodResponse addPaymentMethod(PaymentMethodRequest paymentMethodRequest);
    PaymentMethodResponse searchPaymentByName(String paymentName);
    List<PaymentMethodResponse> gettAllPaymentMethod();
    PaymentMethodResponse updatePayment(PaymentMethodRequest paymentMethodRequest, String paymentName);
    Boolean deletePayment(String paymentName);
}
