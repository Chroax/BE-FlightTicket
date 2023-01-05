package com.binar.finalproject.flightticket.dto;

import com.binar.finalproject.flightticket.model.AuthenticationProvider;
import com.binar.finalproject.flightticket.model.Users;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GoogleLoginRequest {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String googleId;

    public Users toUsersGoogle() {
        Users usersGoogle = new Users();
        usersGoogle.setFullName(this.fullName);
        usersGoogle.setEmail(this.email);
        usersGoogle.setGoogleId(this.googleId);
        usersGoogle.setAuthProvider(AuthenticationProvider.GOOGLE);
        usersGoogle.setStatusActive(true);
        return usersGoogle;
    }
}