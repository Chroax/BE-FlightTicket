package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Orders;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderResponse {
    private UUID orderId;
    private Integer totalTicket;
    private Float totalPrice;
    private String pnrCode;
    private UUID userId;
    private Integer paymentId;

    public static OrderResponse build(Orders orders)
    {
        return OrderResponse.builder()
                .orderId(orders.getOrderId())
                .totalTicket(orders.getTotalTicket())
                .totalPrice(orders.getTotalPrice())
                .pnrCode(orders.getPnrCode())
                .userId(orders.getUsersOrder().getUserId())
                .paymentId(orders.getPaymentMethodsOrder().getPaymentId())
                .build();
    }
}
