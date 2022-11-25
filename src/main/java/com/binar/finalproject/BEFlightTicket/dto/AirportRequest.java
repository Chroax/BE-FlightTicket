package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airports;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AirportRequest {

    private String iataCode;

    @NotEmpty(message = "City name is required.")
    private String airportName;

    public Airports toAirports(){
        return Airports.builder()
                .iataCode(this.iataCode)
                .airportName(this.airportName)
                .build();
    }
}
