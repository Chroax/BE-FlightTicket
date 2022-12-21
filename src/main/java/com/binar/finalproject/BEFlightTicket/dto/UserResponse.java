package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Roles;
import com.binar.finalproject.BEFlightTicket.model.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private UUID userId;
    private String fullName;
    private String email;
    private String telephone;
    private LocalDate birthDate;
    private Boolean gender;

    public static UserResponse build(Users users) {
        return UserResponse.builder()
                .userId(users.getUserId())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .telephone(users.getTelephone())
                .birthDate(users.getBirthDate())
                .gender(users.getGender())
                .build();
    }
}
