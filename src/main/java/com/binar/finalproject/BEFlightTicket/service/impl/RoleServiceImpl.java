package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.RoleRequest;
import com.binar.finalproject.BEFlightTicket.dto.RoleResponse;
import com.binar.finalproject.BEFlightTicket.model.Roles;
import com.binar.finalproject.BEFlightTicket.repository.RoleRepository;
import com.binar.finalproject.BEFlightTicket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponse registerRole(RoleRequest roleRequest) {
        Roles roles = roleRequest.toRoles();

        try {
            roleRepository.save(roles);
            return RoleResponse.build(roles);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<RoleResponse> searchAllRole() {
        List<Roles> allRole = roleRepository.findAll();
        List<RoleResponse> allRoleResponse = new ArrayList<>();
        for (Roles roles: allRole) {
            RoleResponse roleResponse = RoleResponse.build(roles);
            allRoleResponse.add(roleResponse);
        }
        return allRoleResponse;
    }

    @Override
    public RoleResponse updateRole(RoleRequest roleRequest, String roleName) {
        Roles roles = roleRepository.findByName(roleName);
        if(roles != null){
            roles.setRoleName(roleRequest.getRoleName());
            try {
                roleRepository.save(roles);
                return RoleResponse.build(roles);
            }
            catch(Exception exception)
            {
                return null;
            }
        }
        else
            throw new RuntimeException("Roles with name : " + roleName + " not found");
    }

    @Override
    public Boolean deleteRole(String roleName) {
        Roles roles = roleRepository.findByName(roleName);
        if(roles != null) {
            roleRepository.deleteById(roles.getRoleId());
            return true;
        }
        else
            return false;
    }
}
