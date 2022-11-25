package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.AirplanesRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirplanesResponse;

import java.util.List;

public interface AirplanesService {
    AirplanesResponse insertAirplane(AirplanesRequest airplanesRequest);
    AirplanesResponse searchAirplaneByName(String airplaneName);
    List<AirplanesResponse> getAllAirplane();
    AirplanesResponse updateAirplane(AirplanesRequest airplanesRequest, String airplaneName);
    Boolean deleteAirplane (String airplaneName);
}
