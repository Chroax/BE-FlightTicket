package com.binar.finalproject.BEFlightTicket.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    public LoginRequest(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
