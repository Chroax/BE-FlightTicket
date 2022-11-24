package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Roles;
import com.binar.finalproject.BEFlightTicket.model.Users;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {

    private String fullName;
    private String email;
    private String password;
    private String telephone;
    private LocalDate birthDate;
    private Boolean gender;
    private Roles rolesUsers;

    public Users toUsers(Users user) {
        Users users = new Users();
        users.setFullName(user.getFullName());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        users.setTelephone(user.getTelephone());
        users.setBirthDate(user.getBirthDate());
        users.setGender(user.getGender());
        users.setRolesUsers(user.getRolesUsers());
        return users;
    }
}
