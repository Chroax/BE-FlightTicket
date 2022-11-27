package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM passport p where p.traveler_id = :travelerId")
    List<Passport> findAllPassportByTravelerList(@Param("travelerId") UUID travelerId);
}
