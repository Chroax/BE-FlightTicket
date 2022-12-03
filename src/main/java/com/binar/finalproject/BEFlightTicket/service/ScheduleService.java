package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;
import org.springframework.data.domain.Pageable;

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
    List<SearchScheduleResponse> searchAirplaneTicketOrderByLowerPrice(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByHigherPrice(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByEarliestDepartureTime(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByLatestDepartureTime(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByEarliestArrivalTime(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    List<SearchScheduleResponse> searchAirplaneTicketOrderByLatestArrivalTime(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable);
    Iterable<SearchScheduleResponse> findByDepartureArrivalAirportAndDepartureDate(String departureAirport, String arrivalAirport, String departureDate, Pageable pageable);
}
