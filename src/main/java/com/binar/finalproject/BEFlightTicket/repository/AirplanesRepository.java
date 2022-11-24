package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import com.binar.finalproject.BEFlightTicket.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirplanesRepository extends JpaRepository <Airplanes, String> {
    @Query("SELECT r FROM Airplanes r WHERE LOWER(r.airplaneName) LIKE LOWER(:airplaneName)")
    Airplanes findByName(@Param("airplaneName") String roleName);
}
