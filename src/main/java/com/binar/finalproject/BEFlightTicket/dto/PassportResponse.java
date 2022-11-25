package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Passport;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PassportResponse {
    private String passportNumber;
    private LocalDate passportExpiry;
    private String countryCode;
    private UUID travelerId;

    public static PassportResponse build(Passport passport) {
        return PassportResponse.builder()
                .passportNumber(passport.getPassportNumber())
                .passportExpiry(passport.getPassportExpiry())
                .countryCode(passport.getCountriesPassport().getCountryCode())
                .travelerId(passport.getTravelerListPassport().getTravelerId())
                .build();
    }
}
