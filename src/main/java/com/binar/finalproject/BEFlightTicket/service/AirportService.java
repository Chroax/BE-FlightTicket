package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.AirportRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse addAirports(AirportRequest airportRequest);
    List<AirportResponse> searchAllAirports();
    AirportResponse updateAirports(AirportRequest airportRequest, String airportName);
    Boolean deleteAirportsByName(String airportName);
    AirportResponse searchAirportsByName(String airportName);

}
