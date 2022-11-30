package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDetailResponse {

    private UUID orderId;
    private String status;

    private Float totalPrice;
    private String pnrCode;

    @JsonProperty("Order_Date")
    private LocalDateTime createdAt;

    private String airplaneName;

    private String departureCity;
    private String arrivalCity;
    private String departureIATA;
    private String arrivalIATA;
    private String departureTerminal;

    private LocalDate departureDate;
    private LocalTime departureTime;

    private TravelerListResponse travelerListResponses;

    public static OrderDetailResponse build(Orders orders, Airplanes airplanes, Airports departureAirport,
                                            Airports arrivalAirport, Routes routes, Schedules schedules)
    {
        return OrderDetailResponse.builder()
                .orderId(orders.getOrderId())
                .status(orders.getStatus())
                .totalPrice(orders.getTotalPrice())
                .pnrCode(orders.getPnrCode())
                .createdAt(orders.getCreatedAt())
                .airplaneName(airplanes.getAirplaneName())
                .departureCity(routes.getDepartureCity())
                .arrivalCity(routes.getArrivalCity())
                .departureIATA(departureAirport.getIataCode())
                .arrivalIATA(arrivalAirport.getIataCode())
                .departureTerminal(routes.getDepartureTerminal())
                .departureDate(schedules.getDepartureDate())
                .departureTime(schedules.getDepartureTime())
                .build();
    }
}