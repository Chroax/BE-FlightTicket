package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Cities;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CitiesRequest {

    private String cityCode;

    @NotEmpty(message = "City name is required.")
    private String cityName;

    public Cities toCities(){
        return Cities.builder()
                .cityCode(this.cityCode)
                .cityName(this.cityName)
                .build();
    }

}
