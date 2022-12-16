package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.AirportGetAllResponse;
import com.binar.finalproject.BEFlightTicket.dto.AirportRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse addAirports(AirportRequest airportRequest);
    List<AirportGetAllResponse> searchAllAirports();
    AirportResponse updateAirports(AirportRequest airportRequest, String iataCode);
    AirportResponse searchAirportsByName(String airportName);
    List<AirportResponse> searchAirportByCityName(String cityName);
}
