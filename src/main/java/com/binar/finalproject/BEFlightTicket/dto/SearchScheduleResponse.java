package com.binar.finalproject.BEFlightTicket.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class SearchScheduleResponse {
    private UUID scheduleId;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Float price;
    private String status;

    private String airplaneName;
    private String airplaneType;

    private UUID routeId;
    private String departureCity;
    private String arrivalCity;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTerminal;
    private String arrivalTerminal;
}
