package com.binar.finalproject.BEFlightTicket.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String email;
    private String telephone;
    private LocalDate birthDate;
    private Boolean gender;
}
