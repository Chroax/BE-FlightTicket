package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    @Query("SELECT r FROM Roles r WHERE LOWER(r.roleName) LIKE LOWER(:roleName)")
    Roles findByName(@Param("roleName") String roleName);
}
