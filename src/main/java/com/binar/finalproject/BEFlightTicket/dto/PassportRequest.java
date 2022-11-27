package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.Passport;
import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PassportRequest {
    @NotEmpty(message = "passportNumber is required.")
    private String passportNumber;
    @NotEmpty(message = "passportExpiry is required.")
    private LocalDate passportExpiry;
    @NotEmpty(message = "countryCode is required.")
    private String countryCode;
    @NotEmpty(message = "travelerId is required.")
    private UUID travelerId;

    public Passport toPassport(Countries countries, TravelerList travelerList) {
        return Passport.builder()
                .passportNumber(this.passportNumber)
                .passportExpiry(this.passportExpiry)
                .countriesPassport(countries)
                .travelerListPassport(travelerList)
                .build();
    }
}
