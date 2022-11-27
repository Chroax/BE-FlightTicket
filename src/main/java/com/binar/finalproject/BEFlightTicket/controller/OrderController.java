package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addOrder (@RequestBody OrderRequest orderRequest)
    {
        MessageModel messageModel = new MessageModel();
        OrderResponse orderResponse = orderService.addOrder(orderRequest);
        if(orderResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add order");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new order");
            messageModel.setData(orderResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
    @PutMapping("/update/{orderId}")
    public ResponseEntity<MessageModel> updateOrder(@PathVariable UUID orderId, @RequestBody OrderRequest orderRequest) {
        MessageModel messageModel = new MessageModel();
        OrderResponse orderResponse = orderService.updateOrder(orderRequest, orderId);

        if(orderResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update order with id : " + orderId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update order with id : " + orderId);
            messageModel.setData(orderResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
