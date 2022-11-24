package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {

    private Integer roleId;
    private String roleName;

    public Roles toRoles(Roles roles) {
        return Roles.builder()
                .roleId(roles.getRoleId())
                .roleName(roles.getRoleName())
                .build();
    }
}
