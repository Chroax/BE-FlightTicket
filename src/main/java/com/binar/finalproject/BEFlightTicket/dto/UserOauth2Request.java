package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.AuthenticationProvider;
import com.binar.finalproject.BEFlightTicket.model.Users;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserOauth2Request {
    @NotEmpty(message = "fullName is required.")
    private String fullName;
    @NotEmpty(message = "email is required.")
    private String email;
    private AuthenticationProvider authProvider;

    public Users toUsersOAuth2() {
        Users users = new Users();
        users.setFullName(this.fullName);
        users.setEmail(this.email);
        users.setAuthProvider(this.authProvider);
        users.setStatusActive(true);
        return users;
    }
}
