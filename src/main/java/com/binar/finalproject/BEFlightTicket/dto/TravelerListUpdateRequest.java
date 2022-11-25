package com.binar.finalproject.BEFlightTicket.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
