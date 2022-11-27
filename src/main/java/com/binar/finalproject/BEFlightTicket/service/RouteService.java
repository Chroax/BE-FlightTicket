package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    RouteResponse addRoute(RouteRequest routeRequest);
    List<RouteResponse> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);
    List<RouteResponse> findByDepartureAndArrivalAirport(String departureAirport, String arrivalAirport);
    List<RouteResponse> getAllRoute();
    RouteResponse updateRoute(RouteRequest routeRequest, UUID routeId);
    Boolean deleteRoute(UUID routeId);
}
