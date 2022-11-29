package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Schedules;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class ScheduleResponse {
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

    public static ScheduleResponse build(Schedules schedules) {
        return ScheduleResponse.builder()
                .scheduleId(schedules.getScheduleId())
                .departureDate(schedules.getDepartureDate())
                .arrivalDate(schedules.getArrivalDate())
                .departureTime(schedules.getDepartureTime())
                .arrivalTime(schedules.getArrivalTime())
                .price(schedules.getPrice())
                .status(schedules.getStatus())
                .airplaneName(schedules.getAirplanesSchedules().getAirplaneName())
                .routeId(schedules.getRoutesSchedules().getRouteId())
                .build();
    }
    public static ScheduleResponse searchAirplaneTicketSchedule(Schedules schedules){
        return ScheduleResponse.builder()
                .scheduleId(schedules.getScheduleId())
                .departureDate(schedules.getDepartureDate())
                .arrivalDate(schedules.getArrivalDate())
                .departureTime(schedules.getDepartureTime())
                .arrivalTime(schedules.getArrivalTime())
                .price(schedules.getPrice())
                .status(schedules.getStatus())
                .airplaneName(schedules.getAirplanesSchedules().getAirplaneName())
                .airplaneType(schedules.getAirplaneTypeSchedule().getAirplaneType())
                .routeId(schedules.getRoutesSchedules().getRouteId())
                .departureCity(schedules.getDepartureCitySchedule().getDepartureCity())
                .arrivalCity(schedules.getArrivalCitySchedule().getArrivalCity())
                .departureAirport(schedules.getDepartureAirportSchedule().getDepartureAirport())
                .arrivalAirport(schedules.getArrivalAirportSchedule().getArrivalAirport())
                .departureTerminal(schedules.getDepartureTerminalSchedule().getDepartureTerminal())
                .arrivalTerminal(schedules.getArrivalTerminalSchedule().getArrivalTerminal())
                .build();
    }
}
