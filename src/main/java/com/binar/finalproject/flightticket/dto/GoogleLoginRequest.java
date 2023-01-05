package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.AuthenticationProvider;
import com.binar.finalproject.BEFlightTicket.model.Users;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GoogleLoginRequest {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String gooleId;

    public Users toUsersGoogle() {
        Users usersGoogle = new Users();
        usersGoogle.setFullName(this.fullName);
        usersGoogle.setEmail(this.email);
        usersGoogle.setGoogleId(this.gooleId);
        usersGoogle.setAuthProvider(AuthenticationProvider.GOOGLE);
        usersGoogle.setStatusActive(true);
        return usersGoogle;
    }
}
