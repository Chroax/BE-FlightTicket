package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirplanesResponse {
    private String airplaneName;
    private String airplaneType;

    public static AirplanesResponse build(Airplanes airplanes){
        return AirplanesResponse.builder()
                .airplaneName(airplanes.getAirplaneName())
                .airplaneType(airplanes.getAirplaneType())
                .build();
    }
}
