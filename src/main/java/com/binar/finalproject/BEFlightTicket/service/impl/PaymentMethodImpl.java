package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.PaymentMethodRequest;
import com.binar.finalproject.BEFlightTicket.dto.PaymentMethodResponse;
import com.binar.finalproject.BEFlightTicket.model.PaymentMethods;
import com.binar.finalproject.BEFlightTicket.repository.PaymentMethodRepository;
import com.binar.finalproject.BEFlightTicket.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethodResponse addPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        PaymentMethods paymentMethods = paymentMethodRequest.toPaymentMethods();
        try
        {
            paymentMethodRepository.save(paymentMethods);
            return PaymentMethodResponse.build(paymentMethods);
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    @Override
    public PaymentMethodResponse searchPaymentByName(String paymentName) {
        PaymentMethods paymentMethods = paymentMethodRepository.findByName(paymentName);
        if (paymentMethods != null)
            return PaymentMethodResponse.build(paymentMethods);
        else
            return null;
    }

    @Override
    public List<PaymentMethodResponse> gettAllPaymentMethod() {
        List<PaymentMethods> allPayment = paymentMethodRepository.findAll();
        List<PaymentMethodResponse> allPaymentResponse = new ArrayList<>();
        for (PaymentMethods paymentMethods : allPayment)
        {
            PaymentMethodResponse paymentMethodResponse = PaymentMethodResponse.build(paymentMethods);
            allPaymentResponse.add(paymentMethodResponse);
        }
        return allPaymentResponse;
    }

    @Override
    public PaymentMethodResponse updatePayment(PaymentMethodRequest paymentMethodRequest, String paymentName) {
        PaymentMethods paymentMethods = paymentMethodRepository.findByName(paymentName);
        String message = null;
        if (paymentMethods != null)
        {
            if (paymentMethodRequest.getPaymentName() != null)
                paymentMethods.setPaymentName(paymentMethodRequest.getPaymentName());
            else
                message = "Payment with name: "+paymentName+" not found";
            if (paymentMethodRequest.getPaymentType() != null)
                paymentMethods.setPaymentType(paymentMethodRequest.getPaymentType());
            if (paymentMethodRequest.getImagePath() != null)
                paymentMethods.setImagePath(paymentMethodRequest.getImagePath());
            if (message != null)
                return null;
            else
            {
                paymentMethodRepository.save(paymentMethods);
                return PaymentMethodResponse.build(paymentMethods);
            }
        }
        else
            return null;
    }

    @Override
    public Boolean deletePayment(String paymentName) {
        PaymentMethods paymentMethods = paymentMethodRepository.findByName(paymentName);
        if (paymentMethods != null)
        {
            paymentMethodRepository.deleteById(paymentMethods.getPaymentId());
            return true;
        }
        return false;
    }
}
