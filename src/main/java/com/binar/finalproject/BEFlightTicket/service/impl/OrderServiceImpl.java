package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.config.PNRGenerator;
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
                    Orders orders = orderRequest.toOrders(users.get(), paymentMethods.get());
                    try {
                        Float totalPrice = 0f;
                        List<UUID> allSchedulesId = new ArrayList<>();
                        List<Schedules> allSchedules = new ArrayList<>();
                        for (UUID schedulesId: orderRequest.getScheduleId()) {
                            Optional<Schedules> schedules = scheduleRepository.findById(schedulesId);
                            if(schedules.isPresent())
                            {
                                allSchedulesId.add(schedules.get().getScheduleId());
                                allSchedules.add(schedules.get());
                                totalPrice += schedules.get().getPrice();
                            }
                        }
                        orders.setTotalTicket(allSchedulesId.size());
                        orders.setTotalPrice(totalPrice);
                        orders.setScheduleOrders(allSchedules);
                        orderRepository.save(orders);
                        return OrderResponse.build(orders, allSchedulesId);
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

            if(orderRequest.getStatus().equals("ACCEPT")
                    && orders.getPnrCode() == null)
            {
                orders.setStatus(orderRequest.getStatus());
                orders.setPnrCode(PNRGenerator.generatePNR());
            }
            else
                orders.setStatus(orderRequest.getStatus());

            orders.getScheduleOrders().clear();

            Float totalPrice = 0f;
            List<UUID> allSchedulesId = new ArrayList<>();
            List<Schedules> allSchedules = new ArrayList<>();
            for (UUID schedulesId: orderRequest.getScheduleId()) {
                Optional<Schedules> schedules = scheduleRepository.findById(schedulesId);
                if(schedules.isPresent())
                {
                    allSchedulesId.add(schedules.get().getScheduleId());
                    allSchedules.add(schedules.get());
                    totalPrice += schedules.get().getPrice();
                }
            }
            orders.setTotalTicket(allSchedulesId.size());
            orders.setTotalPrice(totalPrice);
            orders.setScheduleOrders(allSchedules);

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
            List<Schedules> schedules = orders.getScheduleOrders();
            List<UUID> schedulesId = new ArrayList<>();

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
