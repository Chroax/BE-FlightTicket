package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Cities;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitiesResponse {

    private String cityCode;
    private String cityName;

    public static CitiesResponse build(Cities cities) {
        return CitiesResponse.builder()
                .cityCode(cities.getCityCode())
                .cityName(cities.getCityName())
                .build();
    }
}
