package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airports;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportResponse {

    private String iataCode;
    private String airportName;

    public static AirportResponse build(Airports airports) {
        return AirportResponse.builder()
                .iataCode(airports.getIataCode())
                .airportName(airports.getAirportName())
                .build();
    }
}
