package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest, UUID scheduleId);
    List<ScheduleResponse> searchAirplaneSchedule(String airplaneName);
    List<ScheduleResponse> searchRouteSchedule(UUID routeId);
    List<SearchScheduleResponse> searchAirplaneTicketSchedule(String arrivalAirport, String departureAirport, String departureDate);
    List<ScheduleResponse> getAllSchedule();
    List<SearchScheduleResponse> searchAirplaneTicketOrderByLowerPrice(String arrivalAirport, String departureAirport, String departureDate);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByHigherPrice(String arrivalAirport, String departureAirport, String departureDate);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByEarliestDepartureTime(String arrivalAirport, String departureAirport, String departureDate);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByLatestDepartureTime(String arrivalAirport, String departureAirport, String departureDate);
}
