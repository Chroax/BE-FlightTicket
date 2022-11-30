package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.OrderRequest;
import com.binar.finalproject.BEFlightTicket.dto.OrderResponse;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.OrderRepository;
import com.binar.finalproject.BEFlightTicket.repository.PaymentMethodRepository;
import com.binar.finalproject.BEFlightTicket.repository.ScheduleRepository;
import com.binar.finalproject.BEFlightTicket.repository.UserRepository;
import com.binar.finalproject.BEFlightTicket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
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
                    List<Schedules> allSchedules = scheduleRepository.findAllById(orderRequest.getScheduleId());
                    Orders orders = orderRequest.toOrders(users.get(), paymentMethods.get(), (Set<Schedules>) allSchedules);
                    try {
                        orderRepository.save(orders);
                        return OrderResponse.build(orders, orderRequest.getScheduleId());
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
            orders.getScheduleOrders().clear();
            List<Schedules> allSchedules = scheduleRepository.findAllById(orderRequest.getScheduleId());
            orders.setScheduleOrders((Set<Schedules>) allSchedules);
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
                return OrderResponse.build(orders, orderRequest.getScheduleId());
            }
        }
        else
            return null;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Orders> allOrder = orderRepository.findAll();
        return listToOrderResponses(allOrder);
    }

    @Override
    public List<OrderResponse> getAllOrderByUserId(UUID userId) {
        List<Orders> allOrder = orderRepository.findAllOrderByUserId(userId);
        return listToOrderResponses(allOrder);
    }

    @Override
    public List<OrderResponse> getAllOrderByPaymentId(Integer paymentId) {
        List<Orders> allOrder = orderRepository.findAllOrderByPaymentId(paymentId);
        return listToOrderResponses(allOrder);
    }

    private List<OrderResponse> listToOrderResponses(List<Orders> allOrder) {
        List<OrderResponse> allOrderResponse = new ArrayList<>();
        for (Orders orders : allOrder)
        {
            Set<Schedules> schedules = orders.getScheduleOrders();
            List<UUID> schedulesId = null;

            for (Schedules schedule : schedules) {
                assert false;
                schedulesId.add(schedule.getScheduleId());
            }
            OrderResponse orderResponse = OrderResponse.build(orders, schedulesId);
            allOrderResponse.add(orderResponse);
        }
        return allOrderResponse;
    }
}
