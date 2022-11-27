package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.OrderRequest;
import com.binar.finalproject.BEFlightTicket.dto.OrderResponse;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.OrderRepository;
import com.binar.finalproject.BEFlightTicket.repository.PaymentMethodRepository;
import com.binar.finalproject.BEFlightTicket.repository.UserRepository;
import com.binar.finalproject.BEFlightTicket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        try{
            Optional<Users> users = userRepository.findById(orderRequest.getUserId());
            Optional<PaymentMethods> paymentMethods = paymentMethodRepository.findById(orderRequest.getPaymentId());

            if(users.isPresent())
            {
                if(paymentMethods.isPresent())
                {
                    Orders orders = orderRequest.toOrders(users.get(), paymentMethods.get());

                    try {
                        orderRepository.save(orders);
                        return OrderResponse.build(orders);
                    }
                    catch(Exception exception)
                    {
                        return null;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }catch (Exception exception)
        {
            return null;
        }
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, UUID orderId) {
        Optional<Orders> isOrders = orderRepository.findById(orderId);
        String message = null;
        if (isOrders.isPresent())
        {
            Orders orders = isOrders.get();
            orders.setTotalTicket(orderRequest.getTotalTicket());
            orders.setTotalPrice(orderRequest.getTotalPrice());
            orders.setPnrCode(orderRequest.getPnrCode());
            orders.setStatus(orderRequest.getStatus());
            Optional<Users> users = userRepository.findById(orderRequest.getUserId());
            if (users.isPresent())
                orders.setUsersOrder(users.get());
            else
                message = "User with this id doesnt exist";
            Optional<PaymentMethods> paymentMethods = paymentMethodRepository.findById(orderRequest.getPaymentId());
            if (paymentMethods.isPresent())
                orders.setPaymentMethodsOrder(paymentMethods.get());
            else
                message = "Payment with this id doesnt exist";

            if (message != null)
                return null;
            else {
                orderRepository.saveAndFlush(orders);
                return OrderResponse.build(orders);
            }
        }
        else
            return null;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Orders> allOrders = orderRepository.findAll();
        List<OrderResponse> allOrderResponse = new ArrayList<>();
        for (Orders orders : allOrders)
        {
            OrderResponse orderResponse = OrderResponse.build(orders);
            allOrderResponse.add(orderResponse);
        }
        return allOrderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrderByUserId(UUID userId) {
        List<Orders> allOrder = orderRepository.findAllOrderByUserId(userId);
        List<OrderResponse> allOrderResponse = new ArrayList<>();
        for (Orders orders : allOrder)
        {
            OrderResponse orderResponse = OrderResponse.build(orders);
            allOrderResponse.add(orderResponse);
        }
        return allOrderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrderByPaymentId(Integer paymentId) {
        List<Orders> allOrder = orderRepository.findAllOrderByPaymentId(paymentId);
        List<OrderResponse> allOrderResponse = new ArrayList<>();
        for (Orders orders : allOrder)
        {
            OrderResponse orderResponse = OrderResponse.build(orders);
            allOrderResponse.add(orderResponse);
        }
        return allOrderResponse;
    }
}
