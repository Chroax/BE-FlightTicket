package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, String> {

    @Query("SELECT u FROM Airports u WHERE LOWER(u.airportName) LIKE LOWER(:airportName)")
    Airports findByAirportName(@Param("airportName") String airportName);
}
