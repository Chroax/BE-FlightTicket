package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.IDCard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class IdCardResponse {
    private String idCardNumber;
    private LocalDate idCardExpiry;
    private String countryCode;
    private UUID travelerId;

    public static IdCardResponse build(IDCard idCard) {
        return IdCardResponse.builder()
                .idCardNumber(idCard.getIdCardNumber())
                .idCardExpiry(idCard.getIdCardExpiry())
                .countryCode(idCard.getCountriesIDCard().getCountryCode())
                .travelerId(idCard.getTravelerListIDCard().getTravelerId())
                .build();
    }
}
