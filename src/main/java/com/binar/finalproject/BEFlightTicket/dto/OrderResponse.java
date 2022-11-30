package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Orders;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {
    private UUID orderId;
    private String status;
    private Integer totalTicket;
    private Float totalPrice;
    private String pnrCode;
    private UUID userId;
    private Integer paymentId;
    private List<UUID> scheduleId;

    public static OrderResponse build(Orders orders, List<UUID> scheduleId)
    {
        return OrderResponse.builder()
                .orderId(orders.getOrderId())
                .status(orders.getStatus())
                .totalTicket(orders.getTotalTicket())
                .totalPrice(orders.getTotalPrice())
                .pnrCode(orders.getPnrCode())
                .userId(orders.getUsersOrder().getUserId())
                .paymentId(orders.getPaymentMethodsOrder().getPaymentId())
                .scheduleId(scheduleId)
                .build();
    }
}
