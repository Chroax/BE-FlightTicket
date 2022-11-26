package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addPayment(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        MessageModel messageModel = new MessageModel();

        PaymentMethodResponse paymentMethodResponse = paymentMethodService.addPaymentMethod(paymentMethodRequest);
        if(paymentMethodResponse == null)
        {
            messageModel.setMessage("Failed add new payment method");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new payment method");
            messageModel.setData(paymentMethodResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
