package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AirplanesRequest {
    @NotEmpty(message = "Airplane name is required.")
    private String airplaneName;

    public Airplanes toAirplanes(){
        return Airplanes.builder()
                .airplaneName(this.airplaneName)
                .build();
    }
}
