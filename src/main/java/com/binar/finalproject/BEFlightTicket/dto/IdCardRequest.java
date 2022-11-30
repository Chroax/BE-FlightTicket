package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.IdCard;
import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class IdCardRequest {
    @NotEmpty(message = "idCardNumber is required.")
    private String idCardNumber;
    @NotEmpty(message = "idCardExpiry is required.")
    private LocalDate idCardExpiry;
    @NotEmpty(message = "countryCode is required.")
    private String countryCode;
    @NotEmpty(message = "travelerId is required.")
    private UUID travelerId;

    public IdCard toIDCard(Countries countries, TravelerList travelerList) {
        return IdCard.builder()
                .idCardNumber(this.idCardNumber)
                .idCardExpiry(this.idCardExpiry)
                .countriesIDCard(countries)
                .travelerListIDCard(travelerList)
                .build();
    }
}
