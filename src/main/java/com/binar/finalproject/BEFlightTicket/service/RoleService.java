package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.RoleRequest;
import com.binar.finalproject.BEFlightTicket.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse registerRole(RoleRequest roleRequest);
    List<RoleResponse> searchAllRole();
    RoleResponse updateRole(RoleRequest roleRequest, String roleName);
    Boolean deleteRole(String roleName);
}
