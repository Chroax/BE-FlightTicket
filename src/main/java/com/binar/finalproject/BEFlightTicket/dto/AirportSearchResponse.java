package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airports;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportSearchResponse {
    private String iataCode;
    private String airportName;
    private String cityName;
    private String countryName;

    public static AirportSearchResponse build(Airports airports, String cityName, String countryName) {
        return AirportSearchResponse.builder()
                .iataCode(airports.getIataCode())
                .airportName(airports.getAirportName())
                .cityName(cityName)
                .countryName(countryName)
                .build();
    }
}
