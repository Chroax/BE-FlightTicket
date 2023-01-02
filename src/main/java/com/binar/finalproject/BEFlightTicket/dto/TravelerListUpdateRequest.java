package com.binar.finalproject.BEFlightTicket.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TravelerListUpdateRequest {
    private String type;
    private String title;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String countryCode;
}
